package mayfifth99.twitter.post.repository.jpa;

import mayfifth99.twitter.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {
    @Modifying
    @Query("UPDATE CommentEntity c "
            + "SET c.content = :#{#commentEntity.getContent()},"
            + " c.updDt = now() "
            + "WHERE c.id = :#{#commentEntity.id}")
    void updateCommentEntity(CommentEntity commentEntity);

    @Modifying
    @Query("UPDATE CommentEntity c "
            + "SET c.likeCount = c.likeCount + :likeCount, "
            + "c.updDt = now() "
            + "WHERE c.id = :id")
    void updateLikeCount(Long id, int likeCount);
}
