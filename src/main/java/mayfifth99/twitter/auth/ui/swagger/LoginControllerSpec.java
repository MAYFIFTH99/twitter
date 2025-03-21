package mayfifth99.twitter.auth.ui.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import mayfifth99.twitter.auth.application.dto.LoginRequestDto;
import mayfifth99.twitter.auth.application.dto.UserAccessTokenResponseDto;
import mayfifth99.twitter.common.ui.Response;

@Tag(name = "로그인 API")
public interface LoginControllerSpec {

    @Operation(summary = "로그인",
            requestBody = @RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "로그인 요청", value = LOGIN_REQUEST)
                                    }
                            )
                    }
            )
    )
    Response<UserAccessTokenResponseDto> login(LoginRequestDto dto);

    String LOGIN_REQUEST = """
            {
                "email": "email@email.com",
                "password": "password",
                "fcmToken": "fcmToken"
            }
            """;
}