package mayfifth99.twitter.message.repository;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.message.application.interfaces.MessageRepository;
import mayfifth99.twitter.message.repository.entity.FcmTokenEntity;
import mayfifth99.twitter.message.repository.jpa.JpaFcmTokenRepository;
import mayfifth99.twitter.user.domain.User;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FcmMessageRepositoryImpl implements MessageRepository {

    private final JpaFcmTokenRepository jpaFcmTokenRepository;
    private static final String LIKE_MESSAGE_TEMPLATE = "%s님이 %님의 글에 좋아요를 눌렀습니다.";
    private static final String MESSAGE_KEY = "message";
    
    @Override
    public void sendLikeMessage(User sendUser, User targetUser) {

        Optional<FcmTokenEntity> tokenEntity = jpaFcmTokenRepository.findById(targetUser.getId());
        if (tokenEntity.isEmpty()) {
            return;
        }

        FcmTokenEntity token = tokenEntity.get();
        Message message = Message.builder()
                .putData(MESSAGE_KEY, LIKE_MESSAGE_TEMPLATE.formatted(sendUser.getName(), targetUser.getName()))
                .setToken(token.getFcmToken())
                .build();
        FirebaseMessaging.getInstance().sendAsync(message);
    }
}
