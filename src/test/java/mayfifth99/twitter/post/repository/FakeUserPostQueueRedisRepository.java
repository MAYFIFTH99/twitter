package mayfifth99.twitter.post.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.post.repository.entity.post.PostEntity;
import mayfifth99.twitter.post.repository.entity.post_queue.UserPostQueueRedisRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Profile("test")
public class FakeUserPostQueueRedisRepository implements UserPostQueueRedisRepository {

    private final Map<Long, Set<PostEntity>> queue = new HashMap<>();

    @Override
    public void publishPostToFollowerUsers(PostEntity postEntity, List<Long> followersId) {
        for (Long userId : followersId) {
            if (queue.containsKey(userId)) {
                queue.get(userId).add(postEntity);
            } else {
                queue.put(userId, new HashSet<>(List.of(postEntity)));
            }
        }
    }

    @Override
    public void publishPostListToFollowingUsers(List<PostEntity> postEntityList, Long userId) {
        if (queue.containsKey(userId)) {
            queue.get(userId).addAll(postEntityList);
        } else {
            queue.put(userId, new HashSet<>(postEntityList));
        }
    }

    @Override
    public void deletePostToUnfollowUser(Long userId, Long targetId) {
        if (queue.containsKey(userId)) {
            queue.get(userId).removeIf(post -> post.getAuthor().getId().equals(targetId));
        }
    }

    public List<PostEntity> getPostsByUserId(Long userId) {
        return List.copyOf(queue.get(userId));
    }
}
