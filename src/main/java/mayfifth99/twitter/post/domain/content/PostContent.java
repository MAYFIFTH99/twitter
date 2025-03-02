package mayfifth99.twitter.post.domain.content;

import lombok.Getter;

@Getter
public class PostContent extends Content{
    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 1000;

    public PostContent(String content) {
        super(content);
    }

    @Override
    void checkText(String content) {
        if(content.isBlank() || content.length() < MIN_LENGTH){
            throw new IllegalArgumentException("최소 5자 이상 입력해주세요.");
        }

        else if(content.length() > MAX_LENGTH){
            throw new IllegalArgumentException("최대 1000자 이하로 입력해주세요.");
        }
    }
}
