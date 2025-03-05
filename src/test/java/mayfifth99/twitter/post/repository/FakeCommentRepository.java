package mayfifth99.twitter.post.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import mayfifth99.twitter.post.application.interfaces.CommentRepository;
import mayfifth99.twitter.post.comment.Comment;

public class FakeCommentRepository implements CommentRepository {

    private final Map<Long, Comment> store = new HashMap<>();

    @Override
    public Comment save(Comment comment) {
        if(comment.getId() != null){
            store.put(comment.getId(), comment);
            return comment;
        }
        long id = store.size() + 1;
        Comment newComment = new Comment(id, comment.getPost(), comment.getAuthor(), comment.getContentObject());
        store.put(id, newComment);
        return newComment;
    }

    @Override
    public Optional<Comment> findById(Long commentId) {
        return Optional.ofNullable(store.get(commentId));
    }

    @Override
    public void delete(Comment comment) {
        store.remove(comment.getId());
    }
}
