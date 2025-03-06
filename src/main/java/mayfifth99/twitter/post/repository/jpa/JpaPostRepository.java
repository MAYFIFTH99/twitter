package mayfifth99.twitter.post.repository.jpa;

import mayfifth99.twitter.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

    @Modifying
    @Query("UPDATE PostEntity p "
            + "SET p.content = :#{#postEntity.getContent()},"
            + " p.state = :#{#postEntity.getState()},"
            + " p.updDt = now() "
            + "WHERE p.id = :#{#postEntity.id}")
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query("UPDATE PostEntity p "
            + "SET p.likeCount = p.likeCount + :likeCount, "
            + "p.updDt = now() "
            + "WHERE p.id = :id")
    void updateLikeCount(Long id, int likeCount);

    @Modifying
    @Query("UPDATE PostEntity p "
            + "SET p.commentCount = p.commentCount +1, "
            + "p.updDt = now() "
            + "WHERE p.id = :id")
    void increaseCommentCount(Long id);
}
