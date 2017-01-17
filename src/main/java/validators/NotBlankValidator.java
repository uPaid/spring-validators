package validators;

import annotations.NotBlank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.*;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Copy of {@link org.hibernate.validator.internal.constraintvalidators.hv.NotBlankValidator} that allows nulls if
 * {@link NotBlank#required()} is set to false.
 *
 * @author Paweł Fiuk
 */

public class NotBlankValidator implements ConstraintValidator<NotBlank, CharSequence> {

    private boolean isRequired;

    public void initialize(NotBlank annotation) {
        isRequired = annotation.required();
    }

    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if (isNull(charSequence)) {
            return !isRequired;
        }
        return isNotBlank(charSequence);
    }

}