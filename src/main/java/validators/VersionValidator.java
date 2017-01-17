package validators;

import annotations.Version;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static com.codepoetics.protonpack.StreamUtils.zip;
import static java.util.Arrays.stream;
import static java.util.Objects.*;
import static java.util.stream.Collectors.toList;

public class VersionValidator implements ConstraintValidator<Version, String> {

    private String minimalVersion;
    private String versionDelimiter;
    private String versionPattern;

    @Override
    public void initialize(Version annotation) {
        minimalVersion = annotation.minimal();
        versionDelimiter = annotation.delimiter();
        versionPattern = "^(\\d+" + versionDelimiter + ")*\\d+$";
    }

    @Override
    public boolean isValid(String actualVersion, ConstraintValidatorContext context) {
        if (isNull(minimalVersion) || !matchesPattern(minimalVersion) || !matchesPattern(actualVersion)) {
            return false;
        }

        if (actualVersion.equals(minimalVersion)) {
            return true;
        }

        List<VersionsPart> versionsParts = splitAndJoinVersionStrings(minimalVersion, actualVersion);

        return versionsParts.stream()
                            .map(VersionsPart::isValid)
                            .reduce((major, minor) -> nonNull(major) ? major : (nonNull(minor) ? minor : true))
                            .orElse(true);
    }

    private boolean matchesPattern(String version) {
        return Pattern.compile(versionPattern)
                      .matcher(version)
                      .matches();
    }

    private List<VersionsPart> splitAndJoinVersionStrings(String expected, String actual) {
        return zip(splitVersionString(expected),
                   splitVersionString(actual),
                   VersionsPart::new)
                .collect(toList());
    }

    private Stream<Integer> splitVersionString(String versionString) {
        return stream(versionString.split(versionDelimiter)).map(Integer::parseInt);
    }

    private class VersionsPart {
        private final Integer expectedMinimal;
        private final Integer actual;

        VersionsPart(Integer expectedMinimal, Integer actual) {
            this.expectedMinimal = expectedMinimal;
            this.actual = actual;
        }

        private Boolean isValid() {
            if (expectedMinimal < actual) {
                return true;
            }
            else if (expectedMinimal > actual) {
                return false;
            }
            else {
                return null;
            }
        }
    }

}
