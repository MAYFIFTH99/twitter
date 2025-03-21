package mayfifth99.twitter.post.ui.controller.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import mayfifth99.twitter.common.principal.UserPrincipal;
import mayfifth99.twitter.common.ui.Response;
import mayfifth99.twitter.post.ui.dto.GetPostContentResponseDto;

@Tag(name = "피드 API")
public interface FeedControllerSpec {

    @Operation(summary = "게시물 조회",
            requestBody = @RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "게시물 조회 요청", value = GET_POST_CONTENT)
                                    }
                            )
                    }
            )
    )
    Response<List<GetPostContentResponseDto>> getPostContent(UserPrincipal userPrincipal,
            Long lastPostId);

    String GET_POST_CONTENT = """
            {
              "userId": 1,
              "userRole": "USER",
              "lastPostId": 1
            }
            """;
}
