package mayfifth99.twitter.user.repository.jpa;

import java.util.List;
import mayfifth99.twitter.user.repository.entity.UserRelationEntity;
import mayfifth99.twitter.user.repository.entity.UserRelationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationId> {

    @Query("select ur.followingUserId from UserRelationEntity ur where ur.followerUserId = :userId")
    List<Long> getFollowers(Long userId);

}
