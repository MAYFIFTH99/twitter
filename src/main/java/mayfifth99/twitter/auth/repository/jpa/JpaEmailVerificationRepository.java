package mayfifth99.twitter.auth.repository.jpa;

import java.util.Optional;
import mayfifth99.twitter.auth.repository.entity.EmailVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEmailVerificationRepository extends JpaRepository<EmailVerificationEntity, Long> {
    Optional<EmailVerificationEntity> findByEmail(String email);
    Optional<EmailVerificationEntity> findByToken(String token);
}
