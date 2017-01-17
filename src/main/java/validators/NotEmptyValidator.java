package validators;

import annotations.NotEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.*;

/**
 * Allows nulls if {@link NotEmpty#required()} is set to false.
 *
 * @author Paweł Fiuk
 */

public class NotEmptyValidator implements ConstraintValidator<NotEmpty, Object> {

    private boolean isRequired;

    public void initialize(NotEmpty annotation) {
        isRequired = annotation.required();
    }

    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        return !isNull(object) || !isRequired;
    }

}