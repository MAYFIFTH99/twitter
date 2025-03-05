package mayfifth99.twitter.post.repository.entity.comment;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mayfifth99.twitter.common.domain.PositiveIntegerCounter;
import mayfifth99.twitter.common.repository.entity.TimeBaseEntity;
import mayfifth99.twitter.post.comment.Comment;
import mayfifth99.twitter.post.domain.content.CommentContent;
import mayfifth99.twitter.post.repository.entity.post.PostEntity;
import mayfifth99.twitter.user.repository.entity.UserEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "community_comment")
public class CommentEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private PostEntity post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    private String content;
    private int likeCount;

    public CommentEntity(PostEntity post, UserEntity author, String content) {
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCount = 0;
    }

    public Comment toComment(){
        return Comment.builder()
                .id(id)
                .post(post.toPost())
                .author(author.toUser())
                .content(new CommentContent(content))
                .likeCount(new PositiveIntegerCounter())
                .build();
    }



}
