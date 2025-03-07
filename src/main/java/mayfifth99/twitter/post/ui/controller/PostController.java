package mayfifth99.twitter.post.ui.controller;

import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.common.ui.Response;
import mayfifth99.twitter.post.application.PostService;
import mayfifth99.twitter.post.application.dto.CreatePostRequestDto;
import mayfifth99.twitter.post.application.dto.LikeRequestDto;
import mayfifth99.twitter.post.application.dto.UpdatePostRequestDto;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public Response<Long> createPost(@RequestBody CreatePostRequestDto dto) {
        return Response.ok(postService.createPost(dto).getId());
    }

    @PatchMapping("/{postId}")
    public Response<Void> updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequestDto dto) {
        postService.updatePost(postId, dto);
        return Response.ok(null);
    }

    @PostMapping("/{postId}/like")
    public Response<Void> likePost(@PathVariable Long postId, @RequestBody LikeRequestDto dto){
        postService.likePost(postId, dto);
        return Response.ok(null);
    }
}
