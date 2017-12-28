package tastypenguinbacon.dontforgetboutit.entities.url;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.URI;

public class UrlValidator implements ConstraintValidator<Url, String> {
    @Override
    public void initialize(Url url) {
    }

    @Override
    public boolean isValid(String urlCandidate, ConstraintValidatorContext context) {
        try {
            new URI(urlCandidate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
