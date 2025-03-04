package mayfifth99.twitter.post.domain;

import lombok.Getter;
import mayfifth99.twitter.common.domain.PositiveIntegerCounter;
import mayfifth99.twitter.post.domain.content.PostContent;
import mayfifth99.twitter.post.domain.content.PostPublicationState;
import mayfifth99.twitter.user.domain.User;

@Getter
public class Post {
    private final Long id;
    private final User author;
    private final PostContent content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;

    public Post(Long id, User author, PostContent content) {
        if(author == null){
            throw new IllegalArgumentException("작성자는 필수입니다.");
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = PostPublicationState.PUBLIC;
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

    public void updatePost(User user, String content, PostPublicationState state){
        if(!author.equals(user)){
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        this.state = state;
        this.content.updateContent(content);

    }

    public int getLikeCount(){
        return likeCount.getCount();
    }
}
