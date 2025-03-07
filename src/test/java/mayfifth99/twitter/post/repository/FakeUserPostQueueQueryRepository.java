package mayfifth99.twitter.post.repository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.post.repository.entity.post.PostEntity;
import mayfifth99.twitter.post.repository.entity.post_queue.UserPostQueueQueryRepository;
import mayfifth99.twitter.post.ui.dto.GetPostContentDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
@RequiredArgsConstructor
public class FakeUserPostQueueQueryRepository implements UserPostQueueQueryRepository {

    private final FakeUserPostQueueRedisRepository fakeRedisRepository;

    @Override
    public List<GetPostContentDto> getContentResponse(Long userId, Long lastPostId) {
        List<PostEntity> postEntities = fakeRedisRepository.getPostsByUserId(userId);

        List<GetPostContentDto> result = new ArrayList<>();
        for (PostEntity postEntity : postEntities) {
            GetPostContentDto res = GetPostContentDto.builder()
                    .id(postEntity.getId())
                    .build();
            result.add(res);
        }
        return result;
    }
}
