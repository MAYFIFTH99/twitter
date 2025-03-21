package mayfifth99.twitter.auth.ui.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import mayfifth99.twitter.auth.application.dto.CreateUserAuthRequestDto;
import mayfifth99.twitter.auth.application.dto.SendEmailRequestDto;
import mayfifth99.twitter.common.ui.Response;

@Tag(name = "로그인 API")
public interface SignUpControllerSpec {

    @Operation(summary = "이메일 전송",
            requestBody = @RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "이메일 인증 요청", value = SEND_EMAIL)

                                    }
                            )
                    }
            )
    )
    Response<Void> sendEmail(SendEmailRequestDto dto);

    @Operation(summary = "이메일 인증",
            requestBody = @RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "이메일 인증", value = VERIFY_EMAIL)

                                    }
                            )
                    }
            )
    )
    Response<Void> verifyEmail(String email, String token);

    @Operation(summary = "회원가입",
            requestBody = @RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "회원가입 요청", value = REGISTER                                            )
                                    }
                            )

                    }
            )
    )
    Response<Long> register(CreateUserAuthRequestDto dto);

    String SEND_EMAIL = """
            {
              "email": "email@email.com"
            }
            """;

    String VERIFY_EMAIL = """
            {
              "email": "email@email.com",
              "token" : "token"
            }
            """;

    String REGISTER = """
            {
              "email": "email@email.com",
              "password": "password",
              "role": "ADMIN",
              "name": "userName",
              "imageUrl": "imageUrl"
            }
            """;
}
