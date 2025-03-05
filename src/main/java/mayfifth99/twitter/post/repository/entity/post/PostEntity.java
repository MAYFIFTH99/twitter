package mayfifth99.twitter.post.repository.entity.post;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mayfifth99.twitter.common.domain.PositiveIntegerCounter;
import mayfifth99.twitter.common.repository.entity.TimeBaseEntity;
import mayfifth99.twitter.post.domain.Post;
import mayfifth99.twitter.post.domain.content.PostContent;
import mayfifth99.twitter.post.domain.content.PostPublicationState;
import mayfifth99.twitter.user.repository.entity.UserEntity;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "community_post")
public class PostEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    private String content;
    private int likeCount;

    @Enumerated(EnumType.STRING)
    private PostPublicationState state;

    public PostEntity(Post post){
        this.id = post.getId();
        this.author = new UserEntity(post.getAuthor());
        this.content = post.getContent().getContent();
        this.likeCount = post.getLikeCount();
        this.state = post.getState();
    }

    public Post toPost(){
        return Post.builder()
                .id(id)
                .author(author.toUser())
                .content(new PostContent(content))
                .likeCount(new PositiveIntegerCounter())
                .state(state)
                .build();
    }

}
