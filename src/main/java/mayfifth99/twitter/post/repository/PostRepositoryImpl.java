package mayfifth99.twitter.post.repository;

import java.util.Optional;
import mayfifth99.twitter.post.application.interfaces.PostRepository;
import mayfifth99.twitter.post.domain.Post;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepositoryImpl implements PostRepository {

    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public Optional<Post> findById(Long postId) {
        return Optional.empty();
    }
}
