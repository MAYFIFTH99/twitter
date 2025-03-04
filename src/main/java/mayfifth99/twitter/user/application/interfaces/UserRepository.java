package mayfifth99.twitter.user.application.interfaces;

import java.util.Optional;
import mayfifth99.twitter.user.domain.User;

public interface UserRepository {

    User save(User user);
    Optional<User> findById(Long userId);

}
