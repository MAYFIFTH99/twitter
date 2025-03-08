package mayfifth99.twitter.post.repository.entity.post_queue;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.post.repository.entity.post.PostEntity;
import mayfifth99.twitter.post.ui.dto.GetPostContentResponseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
@RequiredArgsConstructor
public class UserPostQueueRedisRepositoryImpl implements UserPostQueueRedisRepository{


    @Override
    public void publishPostToFollowerUsers(PostEntity postEntity, List<Long> followersId) {

    }

    @Override
    public void publishPostListToFollowingUsers(List<PostEntity> postEntityList, Long userId) {

    }

    @Override
    public void deletePostToUnfollowUser(Long userId, Long targetId) {

    }

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
        return List.of();
    }
}
