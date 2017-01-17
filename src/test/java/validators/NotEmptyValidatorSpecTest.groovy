package validators

import annotations.NotEmpty
import spock.lang.Specification
import spock.lang.Unroll

class NotEmptyValidatorSpecTest extends Specification {
    @Unroll("should return #expectedValid for #validatedObject if required parameter is set to #required")
    "should validate given object based on required parameter"() {
        given:
        def validator = new NotEmptyValidator()
        def annotation = Mock NotEmpty

        when:
        annotation.required() >> required
        validator.initialize annotation
        def isValid = validator.isValid(validatedObject, null)

        then:
        isValid == expectedValid

        where:
        validatedObject | required | expectedValid
        null            | true     | false
        null            | false    | true
        new Object()    | _        | true
    }
}
