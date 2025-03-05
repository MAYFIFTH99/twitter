package mayfifth99.twitter.post.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import mayfifth99.twitter.post.application.interfaces.PostRepository;
import mayfifth99.twitter.post.domain.Post;

public class FakePostRepository implements PostRepository {

    private final Map<Long, Post> store = new HashMap<>();

    @Override
    public Post save(Post post) {
        if(post.getId() != null){
            store.put(post.getId(), post);
            return post;
        }
        long id = store.size() + 1;
        Post newPost = new Post(id, post.getAuthor(), post.getContent(), post.getState());
        store.put(id, newPost);
        return newPost;
    }

    @Override
    public Optional<Post> findById(Long postId) {
        return Optional.ofNullable(store.get(postId));
    }
}
