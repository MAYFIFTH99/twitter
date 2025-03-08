package mayfifth99.twitter.post.repository.entity.post_queue;

import java.util.List;
import mayfifth99.twitter.post.ui.dto.GetPostContentResponseDto;

public interface UserPostQueueQueryRepository {
    List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId);

}
