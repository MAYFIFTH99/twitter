package mayfifth99.twitter.post.repository.jpa;

import mayfifth99.twitter.post.repository.entity.post.UserPostQueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserPostQueueRepository extends JpaRepository<UserPostQueueEntity, Long> {

    @Modifying
    @Query("DELETE FROM UserPostQueueEntity u WHERE u.userId = :userId AND u.authorId = :targetId")
    void deleteAllByUserIdAndAuthorId(Long userId, Long targetId);
}
