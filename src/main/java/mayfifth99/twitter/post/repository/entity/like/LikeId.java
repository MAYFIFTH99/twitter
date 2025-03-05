package mayfifth99.twitter.post.repository.entity.like;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class LikeId implements Serializable {

    private Long targetId;
    private Long userId;

    private LikeTarget target;

    public LikeId(Long targetId, Long userId, LikeTarget target) {
        this.targetId = targetId;
        this.userId = userId;
        this.target = target;
    }
}
