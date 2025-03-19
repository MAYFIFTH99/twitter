package mayfifth99.twitter.post.repository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.post.application.interfaces.CommentRepository;
import mayfifth99.twitter.post.comment.Comment;
import mayfifth99.twitter.post.domain.Post;
import mayfifth99.twitter.post.repository.entity.comment.CommentEntity;
import mayfifth99.twitter.post.repository.jpa.JpaCommentRepository;
import mayfifth99.twitter.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;
    private final JpaPostRepository jpaPostRepository;

    @Override
    public Optional<Comment> findById(Long commentId) {
        return jpaCommentRepository.findById(commentId).map(CommentEntity::toComment);
    }

    @Override
    public Comment save(Comment comment) {
        Post targetPost = comment.getPost();
        CommentEntity commentEntity = new CommentEntity(comment);
        if (commentEntity.getId() != null) {
            jpaCommentRepository.updateCommentEntity(commentEntity);
            return commentEntity.toComment();
        }
        jpaPostRepository.increaseCommentCount(targetPost.getId());
        return jpaCommentRepository.save(commentEntity).toComment();
    }

    @Override
    public void delete(Comment comment) {
        jpaCommentRepository.deleteById(comment.getId());
    }
}
