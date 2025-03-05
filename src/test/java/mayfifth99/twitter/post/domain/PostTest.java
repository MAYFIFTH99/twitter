package mayfifth99.twitter.post.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import mayfifth99.twitter.post.domain.content.PostContent;
import mayfifth99.twitter.post.domain.content.PostPublicationState;
import mayfifth99.twitter.user.domain.User;
import mayfifth99.twitter.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class PostTest {

    private final UserInfo userInfo = new UserInfo("name", "");
    private final User user1 = new User(1L, userInfo);
    private final User user2 = new User(2L, userInfo);
    private final PostContent postContent = new PostContent("content");

    @Test
    void givenPostCreate_whenLike_thenLikeCountShouldBeOne() throws Exception {
        //given
        Post post = new Post(1L, user1, postContent);

        //when
        post.like(user2);

        //then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenLikeByOtherUser_thenThrowError() throws Exception {
        //when & then
        assertThrows(IllegalArgumentException.class, () -> new Post(1L, user1, postContent).like(user1));
    }

    @Test
    void givenPostCreatedAndLike_whenUnlike_thenLikeCountShouldBeZero() throws Exception {
        //given
        Post post = new Post(1L, user1, postContent);
        post.like(user2);

        //when
        post.unlike(user2);

        //then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreate_whenUnlike_thenLikeCountShouldBeZero() throws Exception {
        //given
        Post post = new Post(1L, user1, postContent);
        post.like(user2);

        //when
        post.unlike(user2);

        //then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreate_whenUpdatePost_thenContentAndStateShouldBeUpdated() throws Exception {
        //given
        Post post = new Post(1L, user1, postContent);
        String updatedContent = "updated content";

        //when
        post.updatePost(user1, updatedContent, PostPublicationState.PRIVATE);

        //then
        assertEquals(updatedContent, post.getContent().getContent());
        assertEquals(PostPublicationState.PRIVATE, post.getState());
    }

    @Test
    void givenPostCreate_whenUpdatePostByOtherUser_thenThrowError() throws Exception {
        //when & then
        assertThrows(IllegalArgumentException.class, () -> new Post(1L, user1, postContent).updatePost(user2, "updated content", PostPublicationState.PRIVATE));
    }
}