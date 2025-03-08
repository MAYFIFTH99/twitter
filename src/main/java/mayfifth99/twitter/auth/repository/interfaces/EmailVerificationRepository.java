package mayfifth99.twitter.auth.repository.interfaces;

import mayfifth99.twitter.auth.domain.EmailVerification;

public interface EmailVerificationRepository {
    void createEmailVerification(EmailVerification emailVerification, String token);
    void verifyEmail(EmailVerification email, String token);
    boolean isEmailVerified(EmailVerification email);
}
