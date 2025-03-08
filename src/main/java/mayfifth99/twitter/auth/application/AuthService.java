package mayfifth99.twitter.auth.application;

import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.auth.application.dto.CreateUserAuthRequestDto;
import mayfifth99.twitter.auth.domain.EmailVerification;
import mayfifth99.twitter.auth.domain.UserAuth;
import mayfifth99.twitter.auth.repository.interfaces.EmailVerificationRepository;
import mayfifth99.twitter.auth.repository.interfaces.UserAuthRepository;
import mayfifth99.twitter.user.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserAuthRepository userAuthRepository;
    private final EmailVerificationRepository emailVerificationRepository;

    public Long registerUser(CreateUserAuthRequestDto dto){
        EmailVerification emailVerification = new EmailVerification(dto.email());

        if(!emailVerificationRepository.isEmailVerified(emailVerification)){
            throw new IllegalArgumentException("인증되지 않은 이메일입니다.");
        }

        UserAuth userAuth = new UserAuth(dto.email(), dto.password(), dto.role());

        User user = new User(dto.name(), dto.imageUrl());
        UserAuth savedUserAuth = userAuthRepository.registerUser(userAuth, user);
        return savedUserAuth.getUserId();
    }
}
