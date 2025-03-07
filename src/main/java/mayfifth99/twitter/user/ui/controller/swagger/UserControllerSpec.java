package mayfifth99.twitter.user.ui.controller.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import mayfifth99.twitter.common.ui.Response;
import mayfifth99.twitter.user.application.dto.CreateUserRequestDto;

@Tag(name = "유저 API")
public interface UserControllerSpec {

    @Operation(summary = "유저 생성",
            requestBody = @RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(name = "유저 생성 요청", value = CREATE_USER_REQUEST)

                                    }
                            )
                    }
            )
    )
    Response<Long> createUser(CreateUserRequestDto dto);

    String CREATE_USER_REQUEST = """
            {
              "name": "userA",
              "profileImageUrl": "example.com"
            }
            """;
}