package mayfifth99.twitter.user.ui.controller.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import mayfifth99.twitter.common.ui.Response;
import mayfifth99.twitter.user.application.dto.FollowUserRequestDto;
import mayfifth99.twitter.user.application.dto.GetUserListResponseDto;


@Tag(name = "팔로우 API")
public interface UserRelationControllerSpec {

    @Operation(summary = "팔로우 요청",
            requestBody = @RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "팔로우 요청", value = FOLLOW_REQUEST)
                                    }
                            )
                    }
            )
    )
    Response<Void> followUser(FollowUserRequestDto dto);

    String FOLLOW_REQUEST = """
            {
              "userId": 1,
              "targetUserId": 2
            }
            """;

    @Operation(summary = "언팔로우 요청",
            requestBody = @RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "언팔로우 요청", value = UNFOLLOW_REQUEST)
                                    }
                            )
                    }
            )
    )
    Response<Void> unfollowUser(FollowUserRequestDto dto);

    String UNFOLLOW_REQUEST = """
            {
              "userId": 1,
              "targetUserId": 2
            }
            """;

    @Operation(summary = "팔로워 리스트 조회",
    requestBody = @RequestBody(
            content = {
                    @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "팔로워 리스트 조회", value = GET_FOLLOWER_LIST)
                            }
                    )
            }
    ))
    Response<List<GetUserListResponseDto>> getFollowerList(Long userId);

    String GET_FOLLOWER_LIST = """
            {
              "userId": 1
            }
            """;

    @Operation(summary = "팔로잉 리스트 조회",
    requestBody = @RequestBody(
            content = {
                    @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "팔로잉 리스트 조회", value = GET_FOLLOWING_LIST)
                            }
                    )
            }
    ))
    Response<List<GetUserListResponseDto>> getFollowingList(Long userId);

    String GET_FOLLOWING_LIST = """
            {
              "userId": 1
            }
            """;
}