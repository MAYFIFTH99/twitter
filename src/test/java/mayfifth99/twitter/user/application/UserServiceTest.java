package mayfifth99.twitter.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import mayfifth99.twitter.fake.FakeObjectFactory;
import mayfifth99.twitter.user.application.dto.CreateUserRequestDto;
import mayfifth99.twitter.user.domain.User;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();

    @Test
    void givenUserInfoDto_whenCreateUser_thenCanFindUser() throws Exception {
        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");

        //when
        User user = userService.createUser(dto);

        //then
        User findUser = userService.getUser(user.getId());
        assertEquals(findUser.getId(), user.getId());
        assertEquals(findUser.getUserInfo().getName(), user.getUserInfo().getName());
    }
}