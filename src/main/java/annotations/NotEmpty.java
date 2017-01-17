package annotations;

import validators.NotEmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Like {@link org.hibernate.validator.constraints.NotEmpty} but without implicit null check.
 * One can specify {@link NotEmpty#required()} parameter to be true in order to apply null validation as in default NotEmpty.
 *
 * @author Paweł Fiuk
 */

@Documented
@Size(min = 1)
@Target(FIELD)
@Retention(RUNTIME)
@ReportAsSingleViolation
@Constraint(validatedBy = NotEmptyValidator.class)
public @interface NotEmpty {

    /**
     * If true, field must not be null.
     */
    boolean required() default true;

    String message() default "{org.hibernate.validator.constraints.NotEmpty.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}