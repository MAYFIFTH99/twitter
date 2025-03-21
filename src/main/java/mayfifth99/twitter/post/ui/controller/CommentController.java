package mayfifth99.twitter.post.ui.controller;

import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.common.ui.Response;
import mayfifth99.twitter.post.application.CommentService;
import mayfifth99.twitter.post.application.dto.CreateCommentRequestDto;
import mayfifth99.twitter.post.application.dto.LikeRequestDto;
import mayfifth99.twitter.post.application.dto.UpdateCommentRequestDto;
import mayfifth99.twitter.post.ui.controller.swagger.CommentControllerSpec;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController implements CommentControllerSpec {

    private final CommentService commentService;

    @PostMapping
    public Response<Long> createComment(@RequestBody CreateCommentRequestDto dto) {
        return Response.ok(commentService.createComment(dto).getId());
    }

    @PatchMapping("/{commentId}")
    public void updateComment(@PathVariable Long commentId, @RequestBody UpdateCommentRequestDto dto) {
        commentService.updateComment(commentId, dto);
    }

    @PostMapping("/{commentId}/like")
    public void likeComment(@PathVariable Long commentId, @RequestBody LikeRequestDto dto) {
        commentService.likeComment(commentId, dto);
    }

    @PostMapping("/{commentId}/unlike")
    public void unlikeComment(@PathVariable Long commentId, @RequestBody LikeRequestDto dto) {
        commentService.unlikeComment(commentId, dto);
    }

}
