package mayfifth99.twitter.acceptance;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import mayfifth99.twitter.acceptance.steps.FeedAcceptanceSteps;
import mayfifth99.twitter.acceptance.utils.AcceptanceTestTemplate;
import mayfifth99.twitter.post.application.dto.CreatePostRequestDto;
import mayfifth99.twitter.post.domain.content.PostPublicationState;
import mayfifth99.twitter.post.ui.dto.GetPostContentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeedAcceptanceTest extends AcceptanceTestTemplate {

    /**
     * User 1 follows -> User 2
     * User 1 follows -> User 3
     */
    @BeforeEach
    void setUp(){
        super.init();
    }

    /**
     * when) User 2 create Post
     * then) User 1 get that Post in Feed
     */
//    @Test
    void givenUserHasFollowerAndCreatePost_whenFollowerUserRequestFeed_thenFollowerCanGetPostFromFeed() throws Exception {
        //given
        // User 2가 글을 작성하는 Step + User 1이 User 2를 팔로우하는 Step 필요
        CreatePostRequestDto postDto = new CreatePostRequestDto(2L, "test content", PostPublicationState.PUBLIC);
        Long createdPostId = FeedAcceptanceSteps.reqCreatePost(postDto);

        //when
        List<GetPostContentDto> results = FeedAcceptanceSteps.reqFeed(1L);

        //then
        assertThat(results.size()).isEqualTo(1);
        assertThat(createdPostId).isEqualTo(results.get(0).getId());
    }
}
