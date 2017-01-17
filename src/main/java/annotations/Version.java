package annotations;

import validators.VersionValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Allows to validate that a version parameter is higher or equal to minimum version.
 * Version consists of numbers separated by {@link Version#delimiter()}.
 *
 * @author Paweł Fiuk
 */

@Documented
@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = VersionValidator.class)
public @interface Version {

    String minimal();

    /**
     * Regex fragment used to build pattern that version is matched against.
     */
    String delimiter() default "\\.";

    String message() default "{version}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
