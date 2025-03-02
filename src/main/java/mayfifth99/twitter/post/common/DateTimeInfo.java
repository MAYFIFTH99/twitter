package mayfifth99.twitter.post.common;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class DateTimeInfo {
    private boolean isEdited;
    private LocalDateTime dateTime;

    public DateTimeInfo() {
        this.isEdited = false;
        this.dateTime = LocalDateTime.now();
    }

    public boolean isEdited() {
        return isEdited;
    }

    public void update(){
        this.isEdited = true;
        dateTime = LocalDateTime.now();
    }

}
