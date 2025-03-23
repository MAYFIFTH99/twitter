package mayfifth99.twitter.post.repository.entity.post_queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.post.repository.entity.post.PostEntity;
import mayfifth99.twitter.post.ui.dto.GetPostContentResponseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
@RequiredArgsConstructor
public class UserPostQueueRedisRepositoryImpl implements UserPostQueueRedisRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private static final int PAGE_SIZE = 10;

    private String getFeedKey(Long userId) {
        return "feed:" + userId;
    }

    @Override
    public void publishPostToFollowerUsers(PostEntity postEntity, List<Long> followersId) {
        String value = postEntity.getAuthor().getId() + ":";

        for (Long followerId : followersId) {
            String key = getFeedKey(followerId);
            redisTemplate.opsForSet().add(key, value);
        }
    }

    @Override
    public void publishPostListToFollowingUsers(List<PostEntity> postEntityList, Long userId) {
        String key = getFeedKey(userId);
        for (PostEntity postEntity : postEntityList) {
            String value = postEntity.getAuthor().getId() + ":" + postEntity.getId();
            redisTemplate.opsForSet().add(key, value);
        }
    }

    @Override
    public void deletePostToUnfollowUser(Long userId, Long targetId) {
        String key = getFeedKey(userId);
        Set<String> posts = redisTemplate.opsForZSet().range(key, 0, -1);
        if (posts != null) {
            for (String post : posts) {
                if (post.startsWith(targetId + ":")) {
                    redisTemplate.opsForZSet().remove(key, post);
                }
            }
        }
    }

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
        String key = getFeedKey(userId);
        Set<String> results;
        // 최신 게시글을 가져오거나, lastPostId보다 작은 (이전) 게시글들을 가져옵니다.
        if (lastPostId == null || lastPostId == 0) {
            results = redisTemplate.opsForZSet().reverseRange(key, 0, PAGE_SIZE - 1);
        } else {
            // lastPostId를 exclusive 하여 그보다 작은 score의 게시글들을 조회
            results = redisTemplate.opsForZSet()
                    .reverseRangeByScore(key, 0, lastPostId - 1, 0, PAGE_SIZE);
        }

        List<GetPostContentResponseDto> dtos = new ArrayList<>();
        if (results != null) {
            for (String value : results) {
                // value 포맷: "authorId:postId"
                String[] parts = value.split(":");
                if (parts.length == 2) {
                    Long authorId = Long.valueOf(parts[0]);
                    Long postId = Long.valueOf(parts[1]);
                    dtos.add(new GetPostContentResponseDto(postId, authorId));
                }
            }
        }
        return dtos;
    }
}
