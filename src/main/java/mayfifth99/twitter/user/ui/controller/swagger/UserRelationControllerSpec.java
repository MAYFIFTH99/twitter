package mayfifth99.twitter.user.ui.controller.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import mayfifth99.twitter.common.ui.Response;
import mayfifth99.twitter.user.application.dto.FollowUserRequestDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;


@Tag(name = "팔로우 API")
public interface UserRelationControllerSpec {

    @Operation(
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

    @Operation(
            requestBody = @RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "언팔로우 요청", value = FOLLOW_REQUEST)
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
}
