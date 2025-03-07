package mayfifth99.twitter.post.repository.entity.like;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mayfifth99.twitter.common.repository.entity.TimeBaseEntity;
import mayfifth99.twitter.post.comment.Comment;
import mayfifth99.twitter.post.domain.Post;
import mayfifth99.twitter.user.domain.User;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "community_like")
public class LikeEntity extends TimeBaseEntity {

    @EmbeddedId
    private LikeId id;

    public LikeEntity(Post post, User user) {
        this.id = new LikeId(post.getId(), user.getId(), LikeTarget.POST);
    }

    public LikeEntity(Comment comment, User user) {
        this.id = new LikeId(comment.getId(), user.getId(), LikeTarget.COMMENT);
    }



}
