package mayfifth99.twitter.post.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.post.repository.entity.post.PostEntity;
import mayfifth99.twitter.post.repository.entity.post_queue.UserPostQueueRedisRepository;
import mayfifth99.twitter.post.ui.dto.GetPostContentResponseDto;
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

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
        Set<PostEntity> postEntities = queue.get(userId);

        if(postEntities == null) {
            return List.of();
        }
        List<PostEntity> pagedPosts = postEntities.stream()
                .filter(post -> post.getId() > lastPostId)
                .sorted((p1, p2) -> Long.compare(p2.getId(), p1.getId()))
                .toList();

        return pagedPosts.stream()
                .map(GetPostContentResponseDto::from)
                .toList();
    }

    public List<PostEntity> getPostsByUserId(Long userId) {
        return List.copyOf(queue.get(userId));
    }
}
