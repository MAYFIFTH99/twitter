package mayfifth99.twitter.post.ui.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.common.principal.AuthPrincipal;
import mayfifth99.twitter.common.principal.UserPrincipal;
import mayfifth99.twitter.common.ui.Response;
import mayfifth99.twitter.post.repository.entity.post_queue.UserPostQueueQueryRepository;
import mayfifth99.twitter.post.ui.controller.swagger.FeedControllerSpec;
import mayfifth99.twitter.post.ui.dto.GetPostContentResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController implements FeedControllerSpec {

    private final UserPostQueueQueryRepository userPostQueueQueryRepository;

    @GetMapping
    public Response<List<GetPostContentResponseDto>> getPostContent(@AuthPrincipal UserPrincipal userPrincipal,
            @RequestParam(required = false) Long lastPostId) {
        return Response.ok(userPostQueueQueryRepository.getContentResponse(userPrincipal.getUserId(), lastPostId));
    }
}
