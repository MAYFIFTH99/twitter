package mayfifth99.twitter.acceptance.utils;

import static mayfifth99.twitter.acceptance.steps.UserAcceptanceSteps.createUser;
import static mayfifth99.twitter.acceptance.steps.UserAcceptanceSteps.followUser;

import mayfifth99.twitter.user.application.dto.CreateUserRequestDto;
import mayfifth99.twitter.user.application.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    public void loadData(){
        CreateUserRequestDto dto = new CreateUserRequestDto("test user", "");
        createUser(dto);
        createUser(dto);
        createUser(dto);

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(1L, 3L));
    }
}
