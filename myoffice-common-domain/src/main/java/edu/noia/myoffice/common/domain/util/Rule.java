package edu.noia.myoffice.common.domain.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Rule {

    public static void condition(Supplier<Boolean> predicate, String exceptionMessage) {
        if (predicate.get()) {
            throw new RuntimeException(exceptionMessage);
        }
    }

    public static Supplier<RuntimeException> violation(String exceptionMessage) {
        return () -> new RuntimeException(exceptionMessage);
    }
}
