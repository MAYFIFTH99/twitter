package mayfifth99.twitter.post.domain.content;

import lombok.Getter;
import mayfifth99.twitter.post.common.DateTimeInfo;

@Getter
public abstract class Content {

    protected String content;
    protected final DateTimeInfo dateTimeInfo;

    public Content(String content) {
        checkText(content);
        this.content = content;
        this.dateTimeInfo = new DateTimeInfo();
    }

    public void updateContent(String content){
        checkText(content);
        this.content = content;
        dateTimeInfo.update();
    }

    abstract void checkText(String content);
}
