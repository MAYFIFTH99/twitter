package mayfifth99.twitter.acceptance;

import static mayfifth99.twitter.acceptance.steps.FeedAcceptanceSteps.reqCreatePost;
import static mayfifth99.twitter.acceptance.steps.FeedAcceptanceSteps.reqFeedCode;
import static mayfifth99.twitter.acceptance.steps.FeedAcceptanceSteps.reqFeedList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import mayfifth99.twitter.acceptance.utils.AcceptanceTestTemplate;
import mayfifth99.twitter.post.application.dto.CreatePostRequestDto;
import mayfifth99.twitter.post.domain.content.PostPublicationState;
import mayfifth99.twitter.post.ui.dto.GetPostContentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeedAcceptanceTest extends AcceptanceTestTemplate {

    private String token;
    /**
     * User 1 follows -> User 2
     * User 1 follows -> User 3
     */
    @BeforeEach
    void setUp(){
        super.init();
        this.token = login("user1@test.com");
    }

    /**
     * when) User 2 create Post
     * then) User 1 get that Post in Feed
     */
    @Test
    void givenUserHasFollowerWhenCreatePostThenFollowerFeedCanGetPost() {
        // given
        CreatePostRequestDto dto = new CreatePostRequestDto(2L, "1 content", PostPublicationState.PUBLIC);
        Long createdPostId = reqCreatePost(dto);

        // when, 팔로워의 피드 요청
        List<GetPostContentResponseDto> result = reqFeedList(this.token);

        // then
        assertEquals(1, result.size());
        assertEquals(createdPostId, result.get(0).getId());
    }

    @Test
    void givenUserHasFollowerAndCreatePostWhenGetPostThenReturnPostWithInvalidToken() {
        // when, 팔로워의 피드 요청
        Integer resultCode = reqFeedCode("invalid token");

        // then
        assertEquals(400, resultCode);
    }
}
