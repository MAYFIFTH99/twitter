package mayfifth99.twitter.post.ui.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.common.ui.Response;
import mayfifth99.twitter.post.repository.entity.post_queue.UserPostQueueRedisRepository;
import mayfifth99.twitter.post.ui.dto.GetPostContentDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {

    private final UserPostQueueRedisRepository userPostQueueRedisRepository;

    @GetMapping("/{userId}")
    public Response<List<GetPostContentDto>> getPostContent(@PathVariable Long userId,
            @RequestParam(required = false) Long lastPostId) {
        return Response.ok(userPostQueueRedisRepository.getContentResponse(userId, lastPostId));
    }
}
