package edu.noia.myoffice.common.domain.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.function.Function;

import static edu.noia.myoffice.common.util.exception.ExceptionSuppliers.notFound;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityFinder {

    public static <T,U> T find(Class<T> clazz, U id, Function<U, Optional<T>> finder) {
        return finder
                .apply(id)
                .orElseThrow(notFound(clazz, id));
    }
}
