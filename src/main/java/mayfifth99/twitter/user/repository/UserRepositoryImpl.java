package mayfifth99.twitter.user.repository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.user.application.interfaces.UserRepository;
import mayfifth99.twitter.user.domain.User;
import mayfifth99.twitter.user.repository.entity.UserEntity;
import mayfifth99.twitter.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity userEntity = new UserEntity(user);
        return jpaUserRepository.save(userEntity).toUser();
    }

    @Override
    public Optional<User> findById(Long userId) {
        return jpaUserRepository.findById(userId).map(UserEntity::toUser);
    }
}
