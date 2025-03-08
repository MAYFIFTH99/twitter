package mayfifth99.twitter.auth.application;

import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.auth.application.dto.SendEmailRequestDto;
import mayfifth99.twitter.auth.domain.EmailVerification;
import mayfifth99.twitter.auth.domain.TokenGenerator;
import mayfifth99.twitter.auth.repository.interfaces.EmailSendRepository;
import mayfifth99.twitter.auth.repository.interfaces.EmailVerificationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final EmailVerificationRepository emailVerificationRepository;
    private final EmailSendRepository emailSendRepository;

    public void sendEmail(SendEmailRequestDto dto){
        EmailVerification emailVerificationDomain = new EmailVerification(dto.email());
        String token = TokenGenerator.generateToken();

        emailSendRepository.sendEmail(emailVerificationDomain, token);
        emailVerificationRepository.createEmailVerification(emailVerificationDomain, token);
    }

    public void verifyEmail(String email, String token){
        EmailVerification emailVerification = new EmailVerification(email);
        emailVerificationRepository.verifyEmail(emailVerification, token);
    }
}
