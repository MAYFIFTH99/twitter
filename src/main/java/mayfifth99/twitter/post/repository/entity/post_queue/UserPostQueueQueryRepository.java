package mayfifth99.twitter.post.repository.entity.post_queue;

import java.util.List;
import mayfifth99.twitter.post.ui.dto.GetPostContentDto;

public interface UserPostQueueQueryRepository {
    List<GetPostContentDto> getContentResponse(Long userId, Long lastPostId);

}
