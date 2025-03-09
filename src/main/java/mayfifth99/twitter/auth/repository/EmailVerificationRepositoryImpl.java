package mayfifth99.twitter.auth.repository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.auth.domain.Email;
import mayfifth99.twitter.auth.repository.entity.EmailVerificationEntity;
import mayfifth99.twitter.auth.repository.interfaces.EmailVerificationRepository;
import mayfifth99.twitter.auth.repository.jpa.JpaEmailVerificationRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {

    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;


    @Override
    @Transactional
    public void createEmailVerification(Email email, String token) {
        String emailAddress = email.getEmailText();

        Optional<EmailVerificationEntity> emailEntity = jpaEmailVerificationRepository.findByEmail(
                emailAddress);

        if (emailEntity.isPresent()) {
            EmailVerificationEntity emailVerificationEntity = emailEntity.get();

            if(emailVerificationEntity.isVerified()){
                throw new IllegalArgumentException("Email already verified");
            }

            emailVerificationEntity.updateToken(token);
            return;
        }

        EmailVerificationEntity emailVerificationEntity = new EmailVerificationEntity(emailAddress,
                token);
        jpaEmailVerificationRepository.save(emailVerificationEntity);
    }

    @Override
    @Transactional
    public void verifyEmail(Email email, String token) {
        String emailText = email.getEmailText();

        EmailVerificationEntity emailVerificationEntity = jpaEmailVerificationRepository.findByEmail(
                emailText).orElseThrow(() -> new IllegalArgumentException("Email not found"));

        if(emailVerificationEntity.isVerified()){
            throw new IllegalArgumentException("Email already verified");
        }

        if (!emailVerificationEntity.isEqual(token)) {
            throw new IllegalArgumentException("Invalid token");
        }
        emailVerificationEntity.verify();
    }

    @Override
    public boolean isEmailVerified(Email email) {
        String emailText = email.getEmailText();

        EmailVerificationEntity emailVerificationEntity = jpaEmailVerificationRepository.findByEmail(
                emailText).orElseThrow(() -> new IllegalArgumentException("인증 요청하지 않은 이메일입니다."));

        return emailVerificationEntity.isVerified();
    }
}
