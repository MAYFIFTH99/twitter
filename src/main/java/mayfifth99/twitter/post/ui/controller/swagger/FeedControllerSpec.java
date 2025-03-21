package mayfifth99.twitter.post.ui.controller.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import mayfifth99.twitter.common.principal.UserPrincipal;
import mayfifth99.twitter.common.ui.Response;
import mayfifth99.twitter.post.ui.dto.GetPostContentResponseDto;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "피드 API")
public interface FeedControllerSpec {

    @Operation(summary = "게시물 조회", description = "사용자가 팔로우하는 유저들의 게시물 조회.")
    Response<List<GetPostContentResponseDto>> getPostContent(
            @Parameter(hidden = true) UserPrincipal userPrincipal, // Auth 헤더로 처리됨
            @Parameter(description = "마지막 게시물 ID (페이징 처리)") @RequestParam(name = "lastPostId", required = false) Long lastPostId
    );
}
