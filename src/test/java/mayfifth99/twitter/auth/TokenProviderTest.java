package mayfifth99.twitter.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import mayfifth99.twitter.auth.domain.TokenProvider;
import mayfifth99.twitter.auth.domain.UserRole;
import org.junit.jupiter.api.Test;

class TokenProviderTest {

    private final String secretKey = "secretKey".repeat(50);
    private final TokenProvider tokenProvider = new TokenProvider(secretKey);

    @Test
    void givenValidUserAndRole_whenCreateToken_thenReturnValueToken() throws Exception {
        //given
        Long userId = 1L;
        UserRole role = UserRole.ADMIN;

        //when
        String token = tokenProvider.createToken(userId, role);

        //then
        assertNotNull(token);
        assertEquals(userId, tokenProvider.getUserId(token));
        assertEquals(role, UserRole.valueOf(tokenProvider.role(token)));
    }

    @Test
    void givenInvalidToken_whenGetGetUserId_thenThrowError() throws Exception {
        //given
        String invalidToken = "invalidToken";

        assertThrows(Exception.class,
                () -> { tokenProvider.getUserId(invalidToken);});

    }
}
