package mayfifth99.twitter.post.domain.content;

public class CommentContent extends Content {
    private static final int MAX_LENGTH = 500;

    public CommentContent(String content) {
        super(content);
    }

    @Override
    void checkText(String content) {
        if(content.length() > MAX_LENGTH){
            throw new IllegalArgumentException("최대 500자 이하로 입력해주세요.");
        }
    }
}
