package edu.noia.myoffice.common.util.validation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

import static edu.noia.myoffice.common.util.exception.ExceptionSuppliers.anyException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Rule {

    public static void condition(Supplier<Boolean> predicate, String violationMessage) {
        if (!predicate.get()) {
            throw anyException(violationMessage).get();
        }
    }

    public static Supplier<RuntimeException> violation(String violationMessage) {
        return anyException(violationMessage);
    }
}
