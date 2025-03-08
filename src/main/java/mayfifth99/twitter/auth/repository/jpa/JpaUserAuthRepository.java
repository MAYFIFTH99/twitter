package mayfifth99.twitter.auth.repository.jpa;

import java.util.Optional;
import mayfifth99.twitter.auth.repository.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserAuthRepository extends JpaRepository<UserAuthEntity, String> {

    Optional<UserAuthEntity> findByEmail(String email);
}
