package mayfifth99.twitter.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import mayfifth99.twitter.user.application.dto.CreateUserRequestDto;
import mayfifth99.twitter.user.application.interfaces.UserRepository;
import mayfifth99.twitter.user.domain.User;
import mayfifth99.twitter.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);

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