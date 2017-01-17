package validators

import annotations.NotBlank
import spock.lang.Specification
import spock.lang.Unroll

class NotBlankValidatorSpecTest extends Specification {
    @Unroll("should return #expectedValid for #validatedObject if required parameter is set to #required")
    "should validate given object based on required parameter"() {
        given:
        def validator = new NotBlankValidator()
        def annotation = Mock NotBlank

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
        ""              | _        | false
        " "             | _        | false
        "   "           | _        | false
        "test"          | _        | true
        "   test"       | _        | true
        "test   "       | _        | true
        "   test   "    | _        | true
    }
}
