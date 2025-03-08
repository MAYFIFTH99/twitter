package mayfifth99.twitter.acceptance.steps;

import io.restassured.RestAssured;
import java.util.List;
import mayfifth99.twitter.post.application.dto.CreatePostRequestDto;
import mayfifth99.twitter.post.ui.dto.GetPostContentResponseDto;
import org.springframework.http.MediaType;

public class FeedAcceptanceSteps {

    public static Long reqCreatePost(CreatePostRequestDto dto) {
        return RestAssured
                .given().log().all()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/post")
                .then().log().all()
                .extract()
                .jsonPath()
                .getObject("value", Long.class);
    }

    public static List<GetPostContentResponseDto> reqFeedList(String token){
        return RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/feed")
                .then().log().all()
                .extract()
                .jsonPath()
                .getList("value", GetPostContentResponseDto.class);
    }

    public static Integer reqFeedCode(String token) {
        return RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .header("Authorization", "Bearer " + token)
                .get("/feed")
                .then().log().all()
                .extract()
                .jsonPath()
                .get("code");
    }
}