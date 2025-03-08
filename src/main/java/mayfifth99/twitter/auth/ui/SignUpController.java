package mayfifth99.twitter.auth.ui;

import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.auth.application.AuthService;
import mayfifth99.twitter.auth.application.EmailVerificationService;
import mayfifth99.twitter.auth.application.dto.CreateUserAuthRequestDto;
import mayfifth99.twitter.auth.application.dto.SendEmailRequestDto;
import mayfifth99.twitter.common.ui.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final EmailVerificationService emailVerificationService;
    private final AuthService authService;


    @PostMapping("/send-verification-email")
    public Response<Void> sendEmail(@RequestBody SendEmailRequestDto dto) {
        emailVerificationService.sendEmail(dto);
        return Response.ok(null);
    }

    @GetMapping("/verify-email")
    public Response<Void> verifyEmail(@RequestParam String email, @RequestParam String token) {
        emailVerificationService.verifyEmail(email, token);
        return Response.ok(null);
    }

    @PostMapping("/register")
    public Response<Long> register(@RequestBody CreateUserAuthRequestDto dto){
        return Response.ok(authService.registerUser(dto));
    }
}
