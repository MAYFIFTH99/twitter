package mayfifth99.twitter.post.repository.entity.post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mayfifth99.twitter.common.repository.entity.TimeBaseEntity;

@Entity
@Table(name = "user_post_queue",
        indexes = {
        @Index(name = "idx_user_post_queue_post_id_user_id", columnList = "post_id, user_id")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserPostQueueEntity extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long postId;
    private Long authorId;

    public UserPostQueueEntity(Long userId, Long postId, Long authorId) {
        this.userId = userId;
        this.postId = postId;
        this.authorId = authorId;
    }

}
