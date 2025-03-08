package mayfifth99.twitter.common.principal;

import lombok.Getter;

@Getter
public class UserPrincipal {

    private Long userId;
    private String userRole;

    public UserPrincipal(Long userId, String userRole) {
        this.userId = userId;
        this.userRole = userRole;
    }
}
