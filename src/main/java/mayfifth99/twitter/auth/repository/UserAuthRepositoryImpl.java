package mayfifth99.twitter.auth.repository;

import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.auth.domain.EmailVerification;
import mayfifth99.twitter.auth.domain.UserAuth;
import mayfifth99.twitter.auth.repository.entity.UserAuthEntity;
import mayfifth99.twitter.auth.repository.interfaces.UserAuthRepository;
import mayfifth99.twitter.auth.repository.jpa.JpaUserAuthRepository;
import mayfifth99.twitter.user.application.interfaces.UserRepository;
import mayfifth99.twitter.user.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserAuthRepositoryImpl implements UserAuthRepository {

    private final JpaUserAuthRepository jpaUserAuthRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserAuth registerUser(UserAuth userAuth, User user) {
        User savedUser = userRepository.save(user);
        UserAuthEntity userAuthEntity = new UserAuthEntity(userAuth, savedUser.getId());
        return jpaUserAuthRepository.save(userAuthEntity).toUserAuth();
    }
}
