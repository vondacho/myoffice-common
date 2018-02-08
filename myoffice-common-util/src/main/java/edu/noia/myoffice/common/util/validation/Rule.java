package edu.noia.myoffice.common.util.validation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Rule {

    public static void condition(Supplier<Boolean> predicate, String violationMessage) {
        if (!predicate.get()) {
            throw new RuntimeException(violationMessage);
        }
    }

    public static Supplier<RuntimeException> violation(String violationMessage) {
        return () -> new RuntimeException(violationMessage);
    }
}
