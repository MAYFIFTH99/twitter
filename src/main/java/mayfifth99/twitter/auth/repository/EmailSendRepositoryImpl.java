package mayfifth99.twitter.auth.repository;

import mayfifth99.twitter.auth.domain.EmailVerification;
import mayfifth99.twitter.auth.repository.interfaces.EmailSendRepository;
import org.springframework.stereotype.Repository;

@Repository
public class EmailSendRepositoryImpl implements EmailSendRepository {

    @Override
    public void sendEmail(EmailVerification email, String token) {
        //TODO
    }
}
