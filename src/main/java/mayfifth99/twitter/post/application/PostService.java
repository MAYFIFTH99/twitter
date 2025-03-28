package mayfifth99.twitter.post.application;

import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.post.application.dto.CreatePostRequestDto;
import mayfifth99.twitter.post.application.dto.LikeRequestDto;
import mayfifth99.twitter.post.application.dto.UpdatePostRequestDto;
import mayfifth99.twitter.post.application.interfaces.LikeRepository;
import mayfifth99.twitter.post.application.interfaces.PostRepository;
import mayfifth99.twitter.post.domain.Post;
import mayfifth99.twitter.user.application.UserService;
import mayfifth99.twitter.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final LikeRepository likeRepository;

    @Transactional
    public Post createPost(CreatePostRequestDto dto) {
        User author = userService.getUser(dto.userId());
        Post post = Post.createPost(author, dto.content(), dto.state());
        return postRepository.save(post);
    }

    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public void updatePost(Long postId, UpdatePostRequestDto dto) {
        Post post = getPost(postId);
        User author = userService.getUser(dto.userId());

        post.updatePost(author, dto.content(), dto.state());
        postRepository.save(post);
    }

    @Transactional
    public void likePost(Long postId, LikeRequestDto dto){
        Post post = getPost(postId);
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(post, user)) {
            return;
        }

        post.like(user);
        likeRepository.like(post, user);
    }

    @Transactional
    public void unlikePost(LikeRequestDto dto){
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(post, user)) {
            post.unlike(user);
            likeRepository.unlike(post, user);
        }
    }

}
