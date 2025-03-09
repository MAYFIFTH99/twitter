package mayfifth99.twitter.auth.repository.interfaces;

import mayfifth99.twitter.auth.domain.Email;

public interface EmailSendRepository {
    void sendEmail(Email email, String token);

}
