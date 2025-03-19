package mayfifth99.twitter.user.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.post.repository.entity.post_queue.UserPostQueueCommandRepository;
import mayfifth99.twitter.user.application.interfaces.UserRelationRepository;
import mayfifth99.twitter.user.domain.User;
import mayfifth99.twitter.user.repository.entity.UserEntity;
import mayfifth99.twitter.user.repository.entity.UserRelationEntity;
import mayfifth99.twitter.user.repository.entity.UserRelationId;
import mayfifth99.twitter.user.repository.jpa.JpaUserRelationRepository;
import mayfifth99.twitter.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpaUserRepository;
    private final UserPostQueueCommandRepository userPostQueueCommandRepository;

    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserRelationId userRelationId = new UserRelationId(user.getId(), targetUser.getId());
        return jpaUserRelationRepository.existsById(userRelationId);
    }

    @Override
    public void save(User user, User targetUser) {
        UserRelationEntity userRelationEntity = UserRelationEntity.builder()
                .followingUserId(user.getId())
                .followerUserId(targetUser.getId())
                .build();
        jpaUserRelationRepository.save(userRelationEntity);
        userPostQueueCommandRepository.saveFollowPost(user.getId(), targetUser.getId());
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));

    }

    @Override
    public void delete(User user, User targetUser) {
        UserRelationId userRelationId = new UserRelationId(user.getId(), targetUser.getId());
        jpaUserRelationRepository.deleteById(userRelationId);
        userPostQueueCommandRepository.deleteUnfollowPost(user.getId(), targetUser.getId());
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
    }

}
