package mayfifth99.twitter.post.comment;

import mayfifth99.twitter.common.domain.PositiveIntegerCounter;
import mayfifth99.twitter.post.domain.Post;
import mayfifth99.twitter.post.domain.content.CommentContent;
import mayfifth99.twitter.user.domain.User;

public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final CommentContent content;
    private final PositiveIntegerCounter likeCount;

    public Comment(Long id, Post post, User author, CommentContent content) {
        if(post == null){
            throw new IllegalArgumentException("게시글은 필수입니다.");
        }
        if(author == null){
            throw new IllegalArgumentException("작성자는 필수입니다.");
        }
        if(content == null){
            throw new IllegalArgumentException("내용은 필수입니다.");
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
    }

    public void like(User user){
        if(author.equals(user)){
            throw new IllegalArgumentException("자신의 글은 좋아요 할 수 없습니다.");
        }
        likeCount.increase();
    }

    public void unlike(User user){
        likeCount.decrease();
    }

    public void updateComment(User user, String content){
        if(!author.equals(user)){
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        this.content.updateContent(content);
    }
}
