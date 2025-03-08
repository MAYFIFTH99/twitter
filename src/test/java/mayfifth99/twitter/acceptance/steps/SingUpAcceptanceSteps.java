package mayfifth99.twitter.acceptance.steps;

import io.restassured.RestAssured;
import mayfifth99.twitter.auth.application.dto.CreateUserAuthRequestDto;
import mayfifth99.twitter.auth.application.dto.SendEmailRequestDto;
import org.springframework.http.MediaType;

public class SingUpAcceptanceSteps {

    public static Integer reqSendEmail(SendEmailRequestDto dto){
        return RestAssured
                .given().log().all()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/signup/send-verification-email")
                .then().log().all()
                .extract()
                .jsonPath().get("code");
    }

    public static Integer reqVerifyEmail(String email, String token){
        return RestAssured
                .given().log().all()
                .queryParam("email", email)
                .queryParam("token", token)
                .when()
                .get("/signup/verify-email")
                .then().log().all()
                .extract()
                .jsonPath().get("code");
    }

    public static Integer registerUser(CreateUserAuthRequestDto dto){
        return RestAssured
                .given().log().all()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/signup/register")
                .then().log().all()
                .extract()
                .jsonPath().get("code");
    }
}
