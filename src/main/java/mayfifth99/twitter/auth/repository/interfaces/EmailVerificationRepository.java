package mayfifth99.twitter.auth.repository.interfaces;

import mayfifth99.twitter.auth.domain.Email;

public interface EmailVerificationRepository {
    void createEmailVerification(Email email, String token);
    void verifyEmail(Email email, String token);
    boolean isEmailVerified(Email email);
}
