package mayfifth99.twitter.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class UserAuth {
    private final EmailVerification email;
    private final Password password;
    private final UserRole userRole;
    private Long userId;


    public UserAuth(String email, String password, UserRole userRole) {
        this.email = new EmailVerification(email);
        this.password = Password.createPassword(password);
        this.userRole = userRole;
    }

    public UserAuth(String email, String password, UserRole userRole, Long userId) {
        this.email = new EmailVerification(email);
        this.password = Password.createEncryptPassword(password);
        this.userRole = userRole;
        this.userId = userId;
    }

    public boolean matchPassword(String password) {
        return this.password.matchPassword(password);
    }
}
