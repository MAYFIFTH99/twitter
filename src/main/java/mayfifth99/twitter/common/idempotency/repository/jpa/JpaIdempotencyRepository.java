package mayfifth99.twitter.common.idempotency.repository.jpa;

import java.util.Optional;
import mayfifth99.twitter.common.idempotency.repository.entity.IdempotencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaIdempotencyRepository extends JpaRepository<IdempotencyEntity, Long> {

    Optional<IdempotencyEntity> findByIdempotencyKey(String key);
}
