package edu.noia.myoffice.common.util.exception;

import java.util.function.Supplier;

public class ExceptionSuppliers {

    public static Supplier<RuntimeException> anyException(String message) {
        return () -> new RuntimeException(message);
    }

    public static Supplier<RuntimeException> notFound(Class clazz) {
        return () -> new RuntimeException(String.format("No %s has been found", clazz));
    }

    public static <U> Supplier<RuntimeException> notFound(Class clazz, U identifier) {
        return () -> new RuntimeException(String.format("No %s identified by %s has been found",
                clazz, identifier.toString()));
    }

    public static <T,U> Supplier<RuntimeException> itemNotFound(Class itemClazz, T itemIdentifier,
                                                                Class containerClazz, U containerIdentifier) {
        return () -> new RuntimeException(
                String.format("No %s identified by %s has been found in %s identified by %s",
                        itemClazz, itemIdentifier.toString(),
                        containerClazz, containerIdentifier.toString()));
    }
}
