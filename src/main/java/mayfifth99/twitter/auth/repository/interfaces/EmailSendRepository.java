package mayfifth99.twitter.auth.repository.interfaces;

import mayfifth99.twitter.auth.domain.EmailVerification;

public interface EmailSendRepository {
    void sendEmail(EmailVerification email, String token);

}
