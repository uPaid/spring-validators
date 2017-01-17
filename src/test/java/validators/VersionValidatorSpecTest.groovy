package validators

import annotations.Version
import spock.lang.Specification
import spock.lang.Unroll

class VersionValidatorSpecTest extends Specification {
    @Unroll("should return #expectedValid for '#validatedObject' if minimal parameter is set to '#minimalVersion' and delimiter is '#delimiter'")
    "should validate given object on minimal version parameter"() {
        given:
        def validator = new VersionValidator()
        def annotation = Mock Version

        when:
        annotation.minimal() >> minimalVersion
        annotation.delimiter() >> delimiter
        validator.initialize annotation
        def isValid = validator.isValid(validatedObject, null)

        then:
        isValid == expectedValid

        where:
        validatedObject | minimalVersion  | delimiter | expectedValid
        "6"             | "4.4"           | "\\."     | true
        "5.0"           | "4.4"           | "\\."     | true
        "4.4.1"         | "4.4"           | "\\."     | true
        "4.4"           | "4.4"           | "\\."     | true
        "4.3.8"         | "4.4"           | "\\."     | false
        "4.3"           | "4.4"           | "\\."     | false
        "4.0"           | "4.4"           | "\\."     | false
        "3"             | "4.4"           | "\\."     | false
        "4-5"           | "4-4"           | "-"       | true
        "4-6"           | "4-4"           | "-"       | true
        "4-3"           | "4-4"           | "-"       | false
        ""              | _               | "\\."     | false
        "."             | _               | "\\."     | false
        "Not a version" | _               | "\\."     | false
        "%#@.!#"        | _               | "\\."     | false
        _ as String     | null            | "\\."     | false
        _ as String     | ""              | "\\."     | false
        _ as String     | "Not a version" | "\\."     | false
        _ as String     | "%#@.!#"        | "\\."     | false
    }
}
