package mayfifth99.twitter.post.application.interfaces;

import mayfifth99.twitter.post.comment.Comment;
import mayfifth99.twitter.post.domain.Post;
import mayfifth99.twitter.user.domain.User;

public interface LikeRepository {

    boolean checkLike(Post post, User user);
    void like(Post post, User user);
    void unlike(Post post, User user);

    boolean checkLike(Comment comment, User user);
    void like(Comment comment, User user);
    void unlike(Comment comment, User user);

}
