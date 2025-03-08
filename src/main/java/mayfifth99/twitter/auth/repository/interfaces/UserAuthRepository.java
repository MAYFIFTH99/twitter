package mayfifth99.twitter.auth.repository.interfaces;

import mayfifth99.twitter.auth.domain.UserAuth;
import mayfifth99.twitter.user.domain.User;

public interface UserAuthRepository {

    UserAuth registerUser(UserAuth userAuth, User user);
    UserAuth findByEmail(String email);
}
