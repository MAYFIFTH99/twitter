package mayfifth99.twitter.acceptance.steps;

import io.restassured.RestAssured;
import mayfifth99.twitter.auth.application.dto.LoginRequestDto;
import mayfifth99.twitter.auth.application.dto.UserAccessTokenResponseDto;
import org.springframework.http.MediaType;

public class LoginAcceptanceSteps {

    public static String reqLoginGetToken(LoginRequestDto dto){
        UserAccessTokenResponseDto res = getLoginResponse(dto);
        return res.accessToken();
    }


    private static UserAccessTokenResponseDto getLoginResponse (LoginRequestDto dto){
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dto)
                .when()
                .post("/login")
                .then()
                .extract()
                .jsonPath()
                .getObject("value", UserAccessTokenResponseDto.class);
    }

    public static Integer reqLoginGetResponseCode(LoginRequestDto dto){
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dto)
                .when()
                .post("/login")
                .then()
                .extract()
                .jsonPath()
                .getObject("code", Integer.class);
    }

}
