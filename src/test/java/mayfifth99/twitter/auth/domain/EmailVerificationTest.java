package mayfifth99.twitter.auth.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class EmailVerificationTest {

    @ParameterizedTest
    @NullAndEmptySource
    void givenNullOrEmptyEmail_whenEmailCreated_thenThrowIllegalArgumentException(String email) {
        assertThrows(IllegalArgumentException.class, () -> new EmailVerification(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalid email", "invalid@.com", "invalid.com", "invalid@com"})
    void givenInvalidEmail_whenEmailCreated_thenThrowIllegalArgumentException(String email) {
        assertThrows(IllegalArgumentException.class, () -> new EmailVerification(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"valid@vaild.com", "alstjr971@naver.com"})
    void givenValidEmail_whenEmailCreated_thenSuccess(String email) {
        EmailVerification emailVerificationText = new EmailVerification(email);

        String savedEmailText = emailVerificationText.getEmailText();

        assertThat(savedEmailText).isEqualTo(email);
    }

}