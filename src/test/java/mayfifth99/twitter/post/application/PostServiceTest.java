package mayfifth99.twitter.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import mayfifth99.twitter.post.application.dto.LikeRequestDto;
import mayfifth99.twitter.post.application.dto.UpdatePostRequestDto;
import mayfifth99.twitter.post.domain.Post;
import mayfifth99.twitter.post.domain.content.PostPublicationState;
import org.junit.jupiter.api.Test;

class PostServiceTest extends PostApplicationTestTemplate {
    @Test
    void givenPostRequestDto_whenCreate_thenReturnPost() throws Exception {
        //when
        Post savedPost = postService.createPost(postDto);

        //then

        Post post = postService.getPost(savedPost.getId());
        assertEquals(post, savedPost);
    }

    @Test
    void givenCreatePost_whenUpdate_thenReturnUpdatedPost() throws Exception {
        //when
        Post savedPost = postService.createPost(postDto);

        //then
        UpdatePostRequestDto updatePostRequestDto = new UpdatePostRequestDto(
                user.getId(), "this is updated", PostPublicationState.PRIVATE);
        postService.updatePost(savedPost.getId(), updatePostRequestDto);

        //then
        Post post = postService.getPost(savedPost.getId());
        assertEquals(post.getContent().getContent(), "this is updated");
        assertEquals(post.getState(), PostPublicationState.PRIVATE);
    }

    @Test
    void givenCreatedPost_whenLiked_thenReturnPostWithLike() throws Exception {
        //given
        Post post = postService.createPost(postDto);

        //when
        LikeRequestDto likeDto = new LikeRequestDto(post.getId(), otherUser.getId());
        postService.likePost(post.getId(), likeDto);

        //then
        Post findPost = postService.getPost(post.getId());
        assertEquals(findPost.getLikeCount(), 1);
    }

    @Test
    void givenCreatedPost_whenLikeTwice_thenReturnLikeCountOne() throws Exception {
        //given
        Post post = postService.createPost(postDto);

        //when
        LikeRequestDto likeDto = new LikeRequestDto(post.getId(), otherUser.getId());
        postService.likePost(post.getId(), likeDto);
        postService.likePost(post.getId(), likeDto);

        //then
        Post findPost = postService.getPost(post.getId());
        assertEquals(findPost.getLikeCount(), 1);
    }

    @Test
    void givenLikedPost_whenUnliked_thenReturnPostWithLike() throws Exception {
        //given
        Post post = postService.createPost(postDto);
        LikeRequestDto likeDto = new LikeRequestDto(post.getId(), otherUser.getId());
        postService.likePost(post.getId(), likeDto);

        //when
        postService.unlikePost(likeDto);

        //then
        Post findPost = postService.getPost(post.getId());
        assertEquals(findPost.getLikeCount(), 0);
    }


    @Test
    void givenCreatedPost_whenUnliked_thenReturnPostWithoutLike() {
        // given
        Post savedPost = postService.createPost(postDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(),otherUser.getId());
        postService.likePost(savedPost.getId(),likeRequestDto);
        postService.unlikePost(likeRequestDto);

        // then
        assertEquals(0, savedPost.getLikeCount());
    }

    @Test
    void givenCreatedPostWhenUnlikedTwiceThenReturnPostWithoutLike() {
        // given
        Post savedPost = postService.createPost(postDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(),otherUser.getId());
        postService.likePost(savedPost.getId(), likeRequestDto);
        postService.unlikePost(likeRequestDto);
        postService.unlikePost(likeRequestDto);

        // then
        assertEquals(0, savedPost.getLikeCount());
    }




}

