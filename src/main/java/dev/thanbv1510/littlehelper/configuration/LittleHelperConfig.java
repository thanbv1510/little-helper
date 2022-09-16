package dev.thanbv1510.littlehelper.configuration;

import dev.thanbv1510.littlehelper.configuration.annotation.PropertyField;
import dev.thanbv1510.littlehelper.configuration.annotation.PropertySource;
import dev.thanbv1510.littlehelper.utils.AccessingAllClassesInPackage;
import dev.thanbv1510.littlehelper.utils.ConvertUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

public class LittleHelperConfig {
    private String pathConf = "./config/";

    private String logConfigFile = "log4j2.xml";

    public void readingConfig() {
        AccessingAllClassesInPackage cxx = new AccessingAllClassesInPackage();
        Set<Class<?>> classSet = cxx.findAllClassesUsingClassLoader("dev.thanbv1510.appbase.config");

        classSet.forEach(clazz -> {
            if (clazz.isAnnotationPresent(PropertySource.class)) {
                PropertySource propertySource = clazz.getAnnotation(PropertySource.class);
                Field[] fields = clazz.getDeclaredFields();
                Arrays.stream(fields).forEach(
                        field -> {
                            try (InputStream input = Files.newInputStream(Paths.get(this.pathConf + propertySource.name()))) {
                                Properties prop = new Properties();
                                prop.load(input);

                                String fieldName = field.getName();
                                Object defaultValue = null;
                                Class<?> type = field.getType();
                                field.setAccessible(true);
                                if (field.isAnnotationPresent(PropertyField.class)) {
                                    PropertyField propertyField = field.getAnnotation(PropertyField.class);
                                    fieldName = propertyField.name();
                                    defaultValue = ConvertUtils.convert(propertyField.defaultValue(), type);

                                }

                                Object value = ConvertUtils.convert(prop.getProperty(fieldName), type);
                                value = value == null ? defaultValue : value;

                                field.set(null, value);

                            } catch (IllegalAccessException | IOException e) {
                                e.printStackTrace();
                            }

                        }
                );
            }
        });

    }
}
