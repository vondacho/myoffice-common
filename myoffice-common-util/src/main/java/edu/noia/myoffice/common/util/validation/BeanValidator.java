package edu.noia.myoffice.common.util.validation;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Optional;

public class BeanValidator {

    private BeanValidator() {
    }

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> T validate(T input) {
        return validate(input, Default.class);
    }

    public static <T> T validate(T input, Class<?>... groups) {
        Optional.of(validator.validate(input, groups))
                .filter(violations -> !violations.isEmpty())
                .ifPresent(violations -> {
                    throw new ConstraintViolationException(violations);
                });
        return input;
    }
}
