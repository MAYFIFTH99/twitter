package mayfifth99.twitter.common.idempotency.repository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.common.idempotency.Idempotency;
import mayfifth99.twitter.common.idempotency.IdempotencyRepository;
import mayfifth99.twitter.common.idempotency.repository.entity.IdempotencyEntity;
import mayfifth99.twitter.common.idempotency.repository.jpa.JpaIdempotencyRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IdempotencyRepositoryImpl implements IdempotencyRepository {

    private final JpaIdempotencyRepository jpaIdempotencyRepository;

    @Override
    public Idempotency getByKey(String key) {
        Optional<IdempotencyEntity> idempotencyEntity = jpaIdempotencyRepository.findByIdempotencyKey(
                key);

        return idempotencyEntity.map(IdempotencyEntity::toIdempotency).orElse(null);
    }

    @Override
    public void save(Idempotency idempotency) {
        jpaIdempotencyRepository.save(new IdempotencyEntity(idempotency));
    }
}
