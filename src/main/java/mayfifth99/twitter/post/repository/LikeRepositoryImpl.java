package mayfifth99.twitter.post.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.message.application.interfaces.MessageRepository;
import mayfifth99.twitter.post.application.interfaces.LikeRepository;
import mayfifth99.twitter.post.comment.Comment;
import mayfifth99.twitter.post.domain.Post;
import mayfifth99.twitter.post.repository.entity.like.LikeEntity;
import mayfifth99.twitter.post.repository.entity.like.LikeId;
import mayfifth99.twitter.post.repository.entity.like.LikeTarget;
import mayfifth99.twitter.post.repository.jpa.JpaCommentRepository;
import mayfifth99.twitter.post.repository.jpa.JpaLikeRepository;
import mayfifth99.twitter.post.repository.jpa.JpaPostRepository;
import mayfifth99.twitter.user.domain.User;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

    /**
     * LikeEntity는 복합 키를 항상 갖고 있기 때문에 save 호출 시 무조건 merge가 발생하여
     * SELECT 쿼리가 한 번 더 나가는 문제가 발생한다.
     * 이를 해결하기 위해 EntityManager를 사용하여 persist를 직접 호출한다.
     */
    @PersistenceContext
    private final EntityManager em;

    private final JpaLikeRepository jpaLikeRepository;
    private final JpaPostRepository jpaPostRepository;
    private final JpaCommentRepository jpaCommentRepository;
    private final MessageRepository messageRepository;

    @Override
    public boolean checkLike(Post post, User user) {
        LikeId likeId = new LikeId(post.getId(), user.getId(), LikeTarget.POST);
        return jpaLikeRepository.existsById(likeId);
    }

    @Override
    public void like(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        em.persist(likeEntity);
        jpaPostRepository.updateLikeCount(post.getId(), 1); // PostEntity를 매개변수로 전달하면 갱신 유실 발생(동시성)
        messageRepository.sendLikeMessage(user, post.getAuthor());
    }

    @Override
    public void unlike(Post post, User user) {
        LikeId likeId = new LikeId(post.getId(), user.getId(), LikeTarget.POST);
        jpaLikeRepository.deleteById(likeId);
        jpaPostRepository.updateLikeCount(post.getId(), -1);
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        LikeId likeId = new LikeId(comment.getId(), user.getId(), LikeTarget.COMMENT);
        return jpaLikeRepository.existsById(likeId);
    }

    @Override
    public void like(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        em.persist(likeEntity);
        jpaCommentRepository.updateLikeCount(comment.getId(), 1);
    }

    @Override
    public void unlike(Comment comment, User user) {
        LikeId likeId = new LikeId(comment.getId(), user.getId(), LikeTarget.COMMENT);
        jpaLikeRepository.deleteById(likeId);
        jpaCommentRepository.updateLikeCount(comment.getId(), -1);
    }
}
