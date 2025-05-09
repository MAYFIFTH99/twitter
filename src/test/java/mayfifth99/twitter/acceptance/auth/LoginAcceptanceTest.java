package mayfifth99.twitter.acceptance.auth;

import static mayfifth99.twitter.acceptance.steps.LoginAcceptanceSteps.requestLoginGetResponseCode;
import static mayfifth99.twitter.acceptance.steps.LoginAcceptanceSteps.requestLoginGetToken;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import mayfifth99.twitter.acceptance.utils.AcceptanceTestTemplate;
import mayfifth99.twitter.auth.application.dto.LoginRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginAcceptanceTest extends AcceptanceTestTemplate {

    private final String email = "email@email.com";

    @BeforeEach
    void setUp() {
        this.cleanUp();
        this.createUser(email);
    }

    @Test
    void givenEmailAndPassword_whenLogin_thenReturnToken() throws Exception {
        //given
        LoginRequestDto dto = new LoginRequestDto(email, "password","fcmToken");

        //when
        String token = requestLoginGetToken(dto);

        //then
        assertNotNull(token);
    }

    @Test
    void givenEmailAndWrongPassword_whenLogin_thenReturnCodeNot200() throws Exception {
        //given
        LoginRequestDto dto = new LoginRequestDto(email, "wrong password","fcmToken");

        //when
        Integer code = requestLoginGetResponseCode(dto);

        //then
        assertEquals(400, code);
    }
}
