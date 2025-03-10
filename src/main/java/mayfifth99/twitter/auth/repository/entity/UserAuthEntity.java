package mayfifth99.twitter.auth.repository.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mayfifth99.twitter.auth.domain.UserAuth;
import mayfifth99.twitter.auth.domain.UserRole;
import mayfifth99.twitter.auth.domain.UserRoleConverter;
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

    @Convert(converter = UserRoleConverter.class)
    private UserRole userRole;

    private LocalDateTime lastLoginDt;

    public void updateLastLoginDt() {
        this.lastLoginDt = LocalDateTime.now();
    }

    public UserAuthEntity(UserAuth userAuth, Long userId) {
        this.email = userAuth.getEmail().getEmailText();
        this.password = userAuth.getPassword().getEncryptedPassword();
        this.userRole = userAuth.getUserRole();
        this.userId = userId;
    }

    public UserAuth toUserAuth() {
        return new UserAuth(email, password, userRole, userId);
    }

}
