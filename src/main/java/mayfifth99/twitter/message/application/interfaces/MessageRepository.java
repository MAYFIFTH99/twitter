package mayfifth99.twitter.message.application.interfaces;

import mayfifth99.twitter.user.domain.User;

public interface MessageRepository {
    void sendLikeMessage(User sendUser, User targetUser);

}
