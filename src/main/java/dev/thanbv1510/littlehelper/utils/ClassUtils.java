package dev.thanbv1510.littlehelper.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClassUtils {
    public static List<Method> findAnnotatedMethodsInClass(final Class<?> clazz,
                                                           final Class<? extends Annotation> annotationClass) {
        return Arrays.stream(clazz.getMethods())
                .filter(method -> method.isAnnotationPresent(annotationClass))
                .collect(Collectors.toList());
    }
}
