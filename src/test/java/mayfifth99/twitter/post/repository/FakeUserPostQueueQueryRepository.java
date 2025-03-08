package mayfifth99.twitter.post.repository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.post.repository.entity.post.PostEntity;
import mayfifth99.twitter.post.repository.entity.post_queue.UserPostQueueQueryRepository;
import mayfifth99.twitter.post.ui.dto.GetPostContentResponseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
@RequiredArgsConstructor
public class FakeUserPostQueueQueryRepository implements UserPostQueueQueryRepository {

    private final FakeUserPostQueueRedisRepository fakeRedisRepository;

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
        List<PostEntity> postEntities = fakeRedisRepository.getPostsByUserId(userId);

        List<GetPostContentResponseDto> result = new ArrayList<>();
        for (PostEntity postEntity : postEntities) {
            GetPostContentResponseDto res = GetPostContentResponseDto.builder()
                    .id(postEntity.getId())
                    .build();
            result.add(res);
        }
        return result;
    }
}
