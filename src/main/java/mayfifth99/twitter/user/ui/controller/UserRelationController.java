package mayfifth99.twitter.user.ui.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.common.ui.Response;
import mayfifth99.twitter.user.application.UserRelationService;
import mayfifth99.twitter.user.application.dto.FollowUserRequestDto;
import mayfifth99.twitter.user.application.dto.GetUserListResponseDto;
import mayfifth99.twitter.user.repository.jpa.JpaUserListQueryRepository;
import mayfifth99.twitter.user.ui.controller.swagger.UserRelationControllerSpec;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relation")
@RequiredArgsConstructor
public class UserRelationController implements UserRelationControllerSpec {

    private final UserRelationService userRelationService;
    private final JpaUserListQueryRepository jpaUserListQueryRepository;

    @PostMapping("/follow")
    public Response<Void> followUser(@RequestBody FollowUserRequestDto dto){
        userRelationService.follow(dto);
        return Response.ok(null);
    }

    @PostMapping("/unfollow")
    public Response<Void> unfollowUser(@RequestBody FollowUserRequestDto dto){
        userRelationService.unfollow(dto);
        return Response.ok(null);
    }

    @GetMapping("/{userId}/follower")
    public Response<List<GetUserListResponseDto>> getFollowerList(@PathVariable Long userId){
        return Response.ok(jpaUserListQueryRepository.getFollowerUserList(userId));
    }

    @GetMapping("/{userId}/following")
    public Response<List<GetUserListResponseDto>> getFollowingList(@PathVariable Long userId){
        return Response.ok(jpaUserListQueryRepository.getFollowingUserList(userId));
    }

}
