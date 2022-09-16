package dev.thanbv1510.littlehelper;

import dev.thanbv1510.littlehelper.utils.ClassUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Than BV
 * @since 1.0.0
 */
public class LittleHelperApplication {
    private static final Log logger = LogFactory.getLog(LittleHelperApplication.class);

    private LittleHelperApplication() {
        throw new IllegalStateException("LittleHelperApplication class");
    }

    public static void run(final Class<?> appClass, final String... args) {
        if (logger.isDebugEnabled()) {
            logger.info("Start application...");
        }

        try {
            Object appObj = appClass.getConstructor().newInstance(args);

            // reading config

            init(appClass, appObj);
            shutdown(appClass, appObj);

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            if (logger.isDebugEnabled()) {
                logger.error("", e);
            }
        }
    }

    private static void init(final Class<?> appClass, final Object object) {
        List<Method> methods = ClassUtils.findAnnotatedMethodsInClass(appClass, Init.class);

        methods.forEach(method -> {
            try {
                method.invoke(object);
            } catch (IllegalAccessException | InvocationTargetException e) {
                if (logger.isDebugEnabled()) {
                    logger.error("", e);
                }
            }
        });
    }

    private static void shutdown(final Class<?> appClass, final Object object) {
        List<Method> shutdownMethods = ClassUtils.findAnnotatedMethodsInClass(appClass, ShutdownHook.class);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> shutdownMethods.forEach(method -> {
            try {
                method.invoke(object);
            } catch (IllegalAccessException | InvocationTargetException e) {
                if (logger.isDebugEnabled()) {
                    logger.error("", e);
                }
            }
        })));
    }
}
