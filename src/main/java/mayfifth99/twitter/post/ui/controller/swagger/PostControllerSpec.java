package mayfifth99.twitter.post.ui.controller.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import mayfifth99.twitter.common.ui.Response;
import mayfifth99.twitter.post.application.dto.CreatePostRequestDto;
import mayfifth99.twitter.post.application.dto.LikeRequestDto;
import mayfifth99.twitter.post.application.dto.UpdatePostRequestDto;

@Tag(name = "게시물 API")
public interface PostControllerSpec {

    @Operation(summary = "게시물 생성",
            requestBody = @RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "게시물 생성 요청", value = CREATE_POST_REQUEST)
                                    }
                            )
                    }
            )
    )
    Response<Long> createPost(CreatePostRequestDto dto);

    @Operation(summary = "게시물 수정",
            requestBody = @RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "게시물 수정 요청", value = UPDATE_POST_REQUEST)
                                    }
                            )
                    }
            )
    )
    Response<Void> updatePost(Long postId, UpdatePostRequestDto dto);

    @Operation(summary = "게시물 좋아요",
            requestBody = @RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "게시물 좋아요 요청", value = LIKE_POST_REQUEST)
                                    }
                            )
                    }
            )
    )
    Response<Void> likePost(Long postId, LikeRequestDto dto);

    String CREATE_POST_REQUEST = """
            {
              "userId": 1,
              "content": "This is a post",
              "state": "PUBLIC"
            }
            """;

    String UPDATE_POST_REQUEST = """
            {
              "userId": 1,
              "content": "Updated post content",
              "state": "PUBLIC"
            }
            """;

    String LIKE_POST_REQUEST = """
            {
              "targetId": 1,
              "userId": 2
            }
            """;
}