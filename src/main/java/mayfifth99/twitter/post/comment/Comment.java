package mayfifth99.twitter.post.comment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import mayfifth99.twitter.common.domain.PositiveIntegerCounter;
import mayfifth99.twitter.post.domain.Post;
import mayfifth99.twitter.post.domain.content.CommentContent;
import mayfifth99.twitter.post.domain.content.Content;
import mayfifth99.twitter.user.domain.User;

@Getter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;


    public static Comment createComment(Post post, User author, String content) {
        return new Comment(null, post, author, new CommentContent(content));
    }

    public Comment(Long id, Post post, User author, Content content) {
        if (post == null) {
            throw new IllegalArgumentException("게시글은 필수입니다.");
        }
        if (author == null) {
            throw new IllegalArgumentException("작성자는 필수입니다.");
        }
        if (content == null) {
            throw new IllegalArgumentException("내용은 필수입니다.");
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
    }

    public void like(User user) {
        if (author.equals(user)) {
            throw new IllegalArgumentException("자신의 글은 좋아요 할 수 없습니다.");
        }
        likeCount.increase();
    }

    public void unlike(User user) {
        likeCount.decrease();
    }


    public void updateComment(User user, String content) {
        if (!author.equals(user)) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        this.content.updateContent(content);
    }

    public String getContent() {
        return content.getContent();
    }
    public Content getContentObject(){
        return content;
    }
    public int getLikeCount() {
        return likeCount.getCount();
    }
}
