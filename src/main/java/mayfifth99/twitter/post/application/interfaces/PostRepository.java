package mayfifth99.twitter.post.application.interfaces;

import java.util.Optional;
import mayfifth99.twitter.post.domain.Post;

public interface PostRepository {

    Post save(Post post);
    Optional<Post> findById(Long postId);


}
