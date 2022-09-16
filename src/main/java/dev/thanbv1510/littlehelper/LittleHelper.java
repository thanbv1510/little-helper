package dev.thanbv1510.littlehelper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Application using little-helper library:
 *  - Auto reading config in file application.yaml
 *  - Using Slf4j and Log4j2 with config in log4j2.xml
 *
 * @author Than BV
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LittleHelper {
}
