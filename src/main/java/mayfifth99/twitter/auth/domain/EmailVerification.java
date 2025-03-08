package mayfifth99.twitter.auth.domain;

import java.util.regex.Pattern;
import lombok.Getter;

@Getter
public class EmailVerification {
    private final static String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private final static Pattern pattern = Pattern.compile(EMAIL_PATTERN);


    private final String emailText;

    public EmailVerification(String emailText) {
        if (emailText == null || emailText.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        checkValidEmail(emailText);
        this.emailText = emailText;
    }

    private void checkValidEmail(String emailText) {
        if (!pattern.matcher(emailText).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
}
