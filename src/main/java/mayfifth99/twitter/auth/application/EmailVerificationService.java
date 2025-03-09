package mayfifth99.twitter.auth.application;

import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.auth.application.dto.SendEmailRequestDto;
import mayfifth99.twitter.auth.domain.Email;
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
        Email emailDomain = new Email(dto.email());
        String token = TokenGenerator.generateToken();

        emailSendRepository.sendEmail(emailDomain, token);
        emailVerificationRepository.createEmailVerification(emailDomain, token);
    }

    public void verifyEmail(String email, String token){
        Email emailVerification = new Email(email);
        emailVerificationRepository.verifyEmail(emailVerification, token);
    }
}
