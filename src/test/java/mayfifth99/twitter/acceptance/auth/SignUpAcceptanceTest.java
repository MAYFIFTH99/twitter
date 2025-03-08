package mayfifth99.twitter.acceptance.auth;

import static mayfifth99.twitter.acceptance.steps.SingUpAcceptanceSteps.registerUser;
import static mayfifth99.twitter.acceptance.steps.SingUpAcceptanceSteps.reqSendEmail;
import static mayfifth99.twitter.acceptance.steps.SingUpAcceptanceSteps.reqVerifyEmail;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import mayfifth99.twitter.acceptance.utils.AcceptanceTestTemplate;
import mayfifth99.twitter.auth.application.dto.CreateUserAuthRequestDto;
import mayfifth99.twitter.auth.application.dto.SendEmailRequestDto;
import mayfifth99.twitter.auth.domain.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SignUpAcceptanceTest extends AcceptanceTestTemplate {

    private final String email = "email@email.com";

    @BeforeEach
    void setUp(){
        cleanUp();
    }

    @Test
    void givenEmail_when_SendEmail_thenVerificationTokenSaved() throws Exception {
        //given
        SendEmailRequestDto dto = new SendEmailRequestDto(email);

        //when
        Integer code = reqSendEmail(dto);

        //then
        String token = getEmailToken(email);
        assertNotNull(token);
        assertThat(code).isEqualTo(200);
    }
    
    @Test
    void givenInvalidEmail_whenEmailSend_thenVerificationTokenNotSaved() throws Exception {
        //given
        SendEmailRequestDto dto = new SendEmailRequestDto("invalid email");

        //when
        Integer code = reqSendEmail(dto);

        //then

        assertThat(code).isEqualTo(400);
    }

    @Test
    void givenSendEmail_whenVerifyEmail_thenEmailVerified() {
        // given
        reqSendEmail(new SendEmailRequestDto(email));

        // when
        String token = getEmailToken(email);
        Integer code = reqVerifyEmail(email, token);

        // then
        boolean isEmailVerified = isEmailVerified(email);
        assertEquals(200, code);
        assertTrue(isEmailVerified);
    }

    @Test
    void givenSendEmail_whenVerifyEmailWithWrongToken_thenEmailNotVerified() {
        // given
        reqSendEmail(new SendEmailRequestDto(email));

        // when
        Integer code = reqVerifyEmail(email, "wrong token");

        // then
        boolean isEmailVerified = isEmailVerified(email);
        assertEquals(500, code);
        assertFalse(isEmailVerified);
    }

    @Test
    void givenSendEmailVerified_whenVerifyAgain_thenThrowError() {
        // given
        reqSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        reqVerifyEmail(email, token);

        // when
        Integer code = reqVerifyEmail(email, token);

        // then
        assertEquals(500, code);
    }

    @Test
    void givenSendEmail_whenVerifyEmailWithWrongEmail_thenThrowError() {
        // given
        reqSendEmail(new SendEmailRequestDto(email));

        // when
        Integer code = reqVerifyEmail("wrong email", "token");

        // then
        assertEquals(400, code);
    }

    @Test
    void givenVerifiedEmail_whenRegister_thenUserRegistered() {
        // given
        reqSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        reqVerifyEmail(email, token);

        // when
        CreateUserAuthRequestDto dto = new CreateUserAuthRequestDto(email, "password", UserRole.USER, "name", "imageUrl");
        Integer code = registerUser(dto);

        // then
        assertEquals(200, code);
        Long userId = getUserId(email);
        assertEquals(1L, userId);
    }

    @Test
    void givenUnverifiedEmail_whenRegister_thenThrowError() {
        // given
        reqSendEmail(new SendEmailRequestDto(email));

        // when
        CreateUserAuthRequestDto dto = new CreateUserAuthRequestDto("email", "password", UserRole.USER, "name", "imageUrl");
        Integer code = registerUser(dto);

        // then
        assertEquals(400, code);
    }
}
