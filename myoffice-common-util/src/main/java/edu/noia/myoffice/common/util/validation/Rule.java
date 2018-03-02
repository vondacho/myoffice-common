package edu.noia.myoffice.common.util.validation;

import java.util.function.Supplier;

import static edu.noia.myoffice.common.util.exception.ExceptionSuppliers.anyException;

public class Rule {

    private Rule() {
    }

    public static void condition(Supplier<Boolean> predicate, String violationMessage) {
        if (!predicate.get()) {
            throw anyException(violationMessage).get();
        }
    }

    public static Supplier<RuntimeException> violation(String violationMessage) {
        return anyException(violationMessage);
    }
}
