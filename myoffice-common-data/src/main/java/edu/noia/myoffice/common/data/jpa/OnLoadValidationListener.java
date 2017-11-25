package edu.noia.myoffice.common.data.jpa;

import javax.persistence.PostLoad;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class OnLoadValidationListener {
    @PostLoad
    public void validate(JpaBaseEntity state) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<JpaBaseEntity>> violations = validator.validate(state);
        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }
}
