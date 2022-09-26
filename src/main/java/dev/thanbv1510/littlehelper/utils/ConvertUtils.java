package dev.thanbv1510.littlehelper.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@UtilityClass
public class ConvertUtils {
    private static final Map<Class<?>, Function<String, ?>> convertData = new HashMap<>();

    static {
        convertData.put(Integer.class, Integer::valueOf);
        convertData.put(Float.class, Float::valueOf);
        convertData.put(Long.class, Long::valueOf);
        convertData.put(Boolean.class, Boolean::valueOf);
        convertData.put(String.class, data -> data);
    }

    public static <T> T convert(String value, Class<T> clazz) {
        try {
            if (value == null) {
                throw new IllegalArgumentException("Cannot cast null value!");
            }

            Function<String, ?> handler = convertData.get(clazz);
            if (handler != null) {
                return clazz.cast(handler.apply(value));
            }

            throw new IllegalArgumentException(String.format("Cannot cast value %s!", value));
        } catch (Exception e) {
            log.error("Error when convert with value: {}, class: {}", value, clazz);
            return null;
        }
    }
}
