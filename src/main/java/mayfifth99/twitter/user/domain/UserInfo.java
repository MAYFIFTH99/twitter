package mayfifth99.twitter.user.domain;

import lombok.Getter;

@Getter
public class UserInfo {
    private static final int NAME_MIN_LENGTH = 2;
    private static final int NAME_MAX_LENGTH = 100;
    
    private final String name;
    private final String imageUrl;

    public UserInfo(String name, String imageUrl) {
        if(name == null || name.isBlank() || name.length() < NAME_MIN_LENGTH || name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException("이름은 2자 이상 100자 이하여야 합니다.");
        }
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
