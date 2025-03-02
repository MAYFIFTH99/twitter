package mayfifth99.twitter.user.domain;

public class UserInfo {
    private final String name;
    private final String imageUrl;

    public UserInfo(String name, String imageUrl) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("이름은 2자 이상 100자 이하여야 합니다.");
        }
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
