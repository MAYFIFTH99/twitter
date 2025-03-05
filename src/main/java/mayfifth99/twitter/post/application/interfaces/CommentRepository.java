package mayfifth99.twitter.post.application.interfaces;

import java.util.Optional;
import mayfifth99.twitter.post.comment.Comment;

public interface CommentRepository {
    Optional<Comment> findById(Long commentId);
    Comment save(Comment comment);
    void delete(Comment comment);
}
