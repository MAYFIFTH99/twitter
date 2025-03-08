package mayfifth99.twitter.auth.ui;

import lombok.RequiredArgsConstructor;
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


    @PostMapping
    public Response<UserAccessTokenResponseDto> login(@RequestBody LoginRequestDto dto) {
        return Response.ok(null);
    }

}
