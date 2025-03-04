package mayfifth99.twitter.user.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class UserInfoTest {

    @Test
    void givenNameAndProfileImageUrl_whenCreated_thenThrowNothing() throws Exception {
        //given
        String name = "test";
        String profileImageUrl = "test";

        //when & then
        assertDoesNotThrow(() -> new UserInfo(name, profileImageUrl));
    }

    @Test
    void givenBlankNameAndProfileImageUrl_whenCreated_thenThrowError() throws Exception {
        //given
        String name = "";
        String profileImageUrl = "test";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> new UserInfo(name, profileImageUrl));
    }

}