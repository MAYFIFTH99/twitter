package mayfifth99.twitter.user.repository.jpa;

import mayfifth99.twitter.user.repository.entity.UserRelationEntity;
import mayfifth99.twitter.user.repository.entity.UserRelationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationId> {

}
