package mayfifth99.twitter.post.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import mayfifth99.twitter.post.domain.Post;
import mayfifth99.twitter.post.domain.content.CommentContent;
import mayfifth99.twitter.post.domain.content.PostContent;
import mayfifth99.twitter.user.domain.User;
import mayfifth99.twitter.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class CommentTest {

    private final User user = new User(1L, new UserInfo("name", "url"));
    private final User otherUser = new User(2L, new UserInfo("name", "url"));

    private final Post post = new Post(1L, user, new PostContent("postContent"));
    private final Comment comment = new Comment(1L, post, user, new CommentContent("CommentContent"));

    @Test
    void givenCommentWhenLikeThenLikeCountShouldBe1() {
        // when
        comment.like(otherUser);

        // then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCommentWhenLikeBySameUserThenLikeCountShouldThrowError() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @Test
    void givenCommentCreatedAndLikeWhenUnlikeThenLikeCountShouldBe0() {
        // given
        comment.getLikeCount();

        // when
        comment.unlike(user);

        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentCreatedWhenUnlikeThenLikeCountShouldBe0() {
        // when
        comment.unlike(user);

        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentWhenUpdateContentThenContentShouldBeUpdated() {
        // given
        String newContent = "new content";

        // when
        comment.updateComment(user, newContent);

        // then
        assertEquals(newContent, comment.getContent());
    }

    @Test
    void givenCommentWhenUpdateContentOver100ThenThrowError() {
        // given
        String newContent = "a".repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(user, newContent));
    }

}