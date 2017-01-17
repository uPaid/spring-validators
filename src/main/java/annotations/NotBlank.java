package annotations;

import validators.NotBlankValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Like {@link org.hibernate.validator.constraints.NotBlank} but without implicit null check.
 * One can specify {@link NotBlank#required()} parameter to be true in order to apply null validation as in default NotBlank.
 *
 * @author Paweł Fiuk
 */

@Documented
@Target(FIELD)
@Retention(RUNTIME)
@ReportAsSingleViolation
@Constraint(validatedBy = NotBlankValidator.class)
public @interface NotBlank {

    /**
     * If true, field must not be null.
     */
    boolean required() default true;

    String message() default "{org.hibernate.validator.constraints.NotBlank.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}