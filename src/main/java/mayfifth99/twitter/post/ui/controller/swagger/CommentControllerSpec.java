package mayfifth99.twitter.post.ui.controller.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import mayfifth99.twitter.common.ui.Response;
import mayfifth99.twitter.post.application.dto.CreateCommentRequestDto;
import mayfifth99.twitter.post.application.dto.LikeRequestDto;
import mayfifth99.twitter.post.application.dto.UpdateCommentRequestDto;

@Tag(name = "댓글 API")
public interface CommentControllerSpec {

    @Operation(summary = "댓글 생성",
            requestBody = @RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "댓글 생성 요청", value = CREATE_COMMENT_REQUEST)
                                    }
                            )
                    }
            )
    )
    Response<Long> createComment(CreateCommentRequestDto dto);

    @Operation(summary = "댓글 수정",
            requestBody = @RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "댓글 수정 요청", value = UPDATE_COMMENT_REQUEST)
                                    }
                            )
                    }
            )
    )
    void updateComment(Long commentId, UpdateCommentRequestDto dto);

    @Operation(summary = "댓글 좋아요",
            requestBody = @RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "댓글 좋아요 요청", value = LIKE_COMMENT_REQUEST)
                                    }
                            )
                    }
            )
    )
    void likeComment(Long commentId, LikeRequestDto dto);

    @Operation(summary = "댓글 좋아요 취소",
            requestBody = @RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "댓글 좋아요 취소 요청", value = UNLIKE_COMMENT_REQUEST)
                                    }
                            )
                    }
            )
    )
    void unlikeComment(Long commentId, LikeRequestDto dto);

    String CREATE_COMMENT_REQUEST = """
            {
              "postId": 1,
                "userId": 2,
              "content": "This is a comment"
            }
            """;

    String UPDATE_COMMENT_REQUEST = """
            {
              "commentId": 1,
              "userId": 2,
              "content": "Updated comment content"
            }
            """;

    String LIKE_COMMENT_REQUEST = """
            {
              "targetId": 1,
              "userId": 2
            }
            """;

    String UNLIKE_COMMENT_REQUEST = """
            {
              "targetId": 1,
              "userId": 2
            }
            """;
}