package mayfifth99.twitter.user.repository.jpa;

import mayfifth99.twitter.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 단순 CUD 기능을 위한 Repository
 */
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

}
