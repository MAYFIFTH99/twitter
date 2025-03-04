package mayfifth99.twitter.user.application;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import mayfifth99.twitter.user.application.dto.CreateUserRequestDto;
import mayfifth99.twitter.user.application.dto.FollowUserRequestDto;
import mayfifth99.twitter.user.application.interfaces.UserRelationRepository;
import mayfifth99.twitter.user.application.interfaces.UserRepository;
import mayfifth99.twitter.user.domain.User;
import mayfifth99.twitter.user.repository.FakeUserRelationRepository;
import mayfifth99.twitter.user.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRelationServiceTest {


    private final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    private final UserRelationService userRelationService = new UserRelationService(userRelationRepository,userService);

    private User user1;
    private User user2;
    private FollowUserRequestDto requestDto;

    @BeforeEach
    void setUp(){
        CreateUserRequestDto dto = new CreateUserRequestDto("test1", "");
        user1 = userService.createUser(dto);
        user2 = userService.createUser(dto);

        requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    void givenCreateTwoUser_whenFollow_thenUserFollowSaved() throws Exception {
        //when
        userRelationService.follow(requestDto);

        //then
        assertEquals(user1.getFollowingCount(), 1);
        assertEquals(user2.getFollowerCount(), 1);
    }

    @Test
    void givenCreateTwoUserFollowed_whenFollow_thenUserThrowError() throws Exception {
        //given
        userRelationService.follow(requestDto);

        //when & then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenFollow_thenUserThrowError() throws Exception {
        //given
        FollowUserRequestDto dto = new FollowUserRequestDto(user1.getId(),
                user1.getId());
        //when & then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(dto));
    }

    @Test
    void givenCreateTwoUser_whenUnfollow_thenUserUnfollowSaved() throws Exception {
        //given
        userRelationService.follow(requestDto);

        //when
        userRelationService.unfollow(requestDto);

        //then
        assertEquals(user1.getFollowingCount(), 0);
        assertEquals(user2.getFollowerCount(), 0);
    }

    @Test
    void givenCreateTwoUser_whenFollow_thenUserThrowError() throws Exception {
        //when & then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenUnfollow_thenUserThrowError() throws Exception {
        //given
        FollowUserRequestDto dto = new FollowUserRequestDto(user1.getId(),
                user1.getId());
        //when & then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(dto));
    }
}