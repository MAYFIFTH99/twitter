package mayfifth99.twitter.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import mayfifth99.twitter.fake.FakeObjectFactory;
import mayfifth99.twitter.post.application.dto.CreateCommentRequestDto;
import mayfifth99.twitter.post.application.dto.LikeRequestDto;
import mayfifth99.twitter.post.application.dto.UpdateCommentRequestDto;
import mayfifth99.twitter.post.comment.Comment;
import org.junit.jupiter.api.Test;

class CommentServiceTest extends PostApplicationTestTemplate {

    private final CommentService commentService = FakeObjectFactory.getCommentService();
    private final CreateCommentRequestDto commentDto = new CreateCommentRequestDto(post.getId(), user.getId(), "this is comment");

    @Test
    void givenCreateCommentRequestDto_whenCreateComment_thenReturnComment() throws Exception {
        //when
        Comment comment = commentService.createComment(commentDto);

        //then
        Comment findComment = commentService.getComment(comment.getId());
        assertEquals(findComment, comment);
        assertEquals(findComment.getContentObject(), comment.getContentObject());
    }

    @Test
    void givenCreateComment_whenUpdateComment_thenReturnUpdatedComment() throws Exception {
        //given
        Comment comment = commentService.createComment(commentDto);

        //when
        UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(comment.getId(),
                user.getId(), "this is updated comment");
        commentService.updateComment(updateCommentRequestDto);

        //then
        Comment findComment = commentService.getComment(comment.getId());
        assertEquals(findComment.getContentObject().getContent(), "this is updated comment");
    }

    @Test
    void givenCreateComment_whenLikeComment_thenReturnCommentWithLike() throws Exception {
        //given
        Comment comment = commentService.createComment(commentDto);

        //when
        LikeRequestDto likeDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeDto);

        //then
        Comment findComment = commentService.getComment(comment.getId());
        assertEquals(findComment.getLikeCount(), 1);
    }

    @Test
    void givenCreateComment_whenLikeTwice_thenReturnLikeCountOne() throws Exception {
        //given
        Comment comment = commentService.createComment(commentDto);

        //when
        LikeRequestDto likeDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeDto);
        commentService.likeComment(likeDto);

        //then
        Comment findComment = commentService.getComment(comment.getId());
        assertEquals(findComment.getLikeCount(), 1);
    }

    @Test
    void givenCreateComment_whenUnlikeComment_thenReturnCommentWithUnlike() throws Exception {
        //given
        Comment comment = commentService.createComment(commentDto);

        //when
        LikeRequestDto likeDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeDto);
        commentService.unlikeComment(likeDto);

        //then
        Comment findComment = commentService.getComment(comment.getId());
        assertEquals(findComment.getLikeCount(), 0);
    }



}
