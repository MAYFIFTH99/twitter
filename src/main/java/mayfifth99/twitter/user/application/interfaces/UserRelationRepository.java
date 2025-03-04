package mayfifth99.twitter.user.application.interfaces;

import mayfifth99.twitter.user.domain.User;

public interface UserRelationRepository {

    boolean isAlreadyFollow(User user, User targetUser);
    void save(User user, User targetUser);
    void delete(User user, User targetUser);

}
