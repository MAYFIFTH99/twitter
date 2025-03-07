package mayfifth99.twitter.post.repository.entity.post_queue;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mayfifth99.twitter.post.repository.entity.post.PostEntity;
import mayfifth99.twitter.post.repository.entity.post.UserPostQueueEntity;
import mayfifth99.twitter.post.repository.jpa.JpaPostRepository;
import mayfifth99.twitter.post.repository.jpa.JpaUserPostQueueRepository;
import mayfifth99.twitter.user.repository.entity.UserEntity;
import mayfifth99.twitter.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository{

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaPostRepository jpaPostRepository;
    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity userEntity = postEntity.getAuthor();
        // 나를 팔로우하는 사람들의 정보 필요
        List<Long> followersId = jpaUserRelationRepository.getFollowers(
                userEntity.getId());

        List<UserPostQueueEntity> userPostQueueEntityList = followersId.stream()
                .map(followerId -> new UserPostQueueEntity(followerId, postEntity.getId(),
                        userEntity.getId()))
                .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        List<PostEntity> postEntityList = jpaPostRepository.findAllByAuthorId(targetId);

        List<UserPostQueueEntity> userPostQueueEntityList = postEntityList.stream()
                .map(postEntity -> new UserPostQueueEntity(userId, postEntity.getId(), targetId))
                .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
    }

    @Override
    @Transactional
    public void deleteUnfollowPost(Long userId, Long targetId) {
        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
    }
}
