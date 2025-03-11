package mayfifth99.twitter.auth.ui;

import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.auth.application.UserAuthService;
import mayfifth99.twitter.auth.application.dto.LoginRequestDto;
import mayfifth99.twitter.auth.application.dto.UserAccessTokenResponseDto;
import mayfifth99.twitter.common.ui.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final UserAuthService userAuthService;

    @PostMapping
    public Response<UserAccessTokenResponseDto> login(@RequestBody LoginRequestDto dto) {
        UserAccessTokenResponseDto token = userAuthService.loginUser(dto);
        System.out.println(dto.fcmToken());
        return Response.ok(token);
    }

}
