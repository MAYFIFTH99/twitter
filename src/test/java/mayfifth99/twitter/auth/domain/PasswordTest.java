package mayfifth99.twitter.auth.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class PasswordTest {

    @Test
    void givenPassword_whenMatchSamePassword_thenReturnTrue() throws Exception {
        //given
        Password password = Password.createEncryptPassword("password");

        //when
        boolean res = password.matchPassword("password");

        //then
        assertTrue(res);
    }

    @Test
    void givenPassword_whenMatchDiffPassword_thenReturnFalse() throws Exception {
        //given
        Password password = Password.createEncryptPassword("password");

        //when
        boolean res = password.matchPassword("diff password");

        //then
        assertFalse(res);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenEmptyPassword_whenCreateEncryptPassword_thenThrowException(String password) {
        //then
        assertThrows(IllegalArgumentException.class, () -> Password.createEncryptPassword(password));
    }

}