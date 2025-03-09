package mayfifth99.twitter.auth.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mayfifth99.twitter.auth.domain.EmailVerification;
import mayfifth99.twitter.auth.domain.Password;
import mayfifth99.twitter.auth.domain.UserAuth;
import mayfifth99.twitter.auth.domain.UserRole;
import mayfifth99.twitter.common.repository.entity.TimeBaseEntity;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Table(name = "community_user_auth")
public class UserAuthEntity extends TimeBaseEntity{

    @Id
    private String email;
    private String password;
    private Long userId;

    @Enumerated
    private UserRole userRole;

    public UserAuthEntity(UserAuth userAuth, Long userId) {
        this.email = userAuth.getEmail().getEmailText();
        this.password = userAuth.getPassword().getEncryptedPassword();
        this.userRole = userAuth.getUserRole();
        this.userId = userId;
    }

    public UserAuth toUserAuth() {
        return UserAuth.builder()
                .email(new EmailVerification(email))
                .password(Password.createEncryptPassword(password))
                .userRole(userRole)
                .userId(userId)
                .build();
    }

}
