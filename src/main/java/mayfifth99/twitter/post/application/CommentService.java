package mayfifth99.twitter.post.application;

import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.post.application.dto.CreateCommentRequestDto;
import mayfifth99.twitter.post.application.dto.LikeRequestDto;
import mayfifth99.twitter.post.application.dto.UpdateCommentRequestDto;
import mayfifth99.twitter.post.application.interfaces.CommentRepository;
import mayfifth99.twitter.post.application.interfaces.LikeRepository;
import mayfifth99.twitter.post.comment.Comment;
import mayfifth99.twitter.post.domain.Post;
import mayfifth99.twitter.user.application.UserService;
import mayfifth99.twitter.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;
    private final LikeRepository likeRepository;

    public Comment getComment(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public Comment createComment(CreateCommentRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Post post = postService.getPost(dto.postId());

        Comment comment = Comment.createComment(post,user, dto.content());
        return commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Long commentId, UpdateCommentRequestDto dto){
        User user = userService.getUser(dto.userId());
        Comment comment = getComment(commentId);

        comment.updateComment(user, dto.content());
        commentRepository.save(comment);
    }

    @Transactional
    public void likeComment(Long commentId, LikeRequestDto dto) {
        Comment comment = getComment(commentId);
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(comment, user)) {
            return;
        }

        comment.like(user);
        likeRepository.like(comment, user);
    }

    @Transactional
    public void unlikeComment(Long commentId, LikeRequestDto dto) {
        Comment comment = getComment(commentId);
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(comment, user)) {
            comment.unlike(user);
            likeRepository.unlike(comment, user);
        }


    }

}
