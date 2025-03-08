package mayfifth99.twitter.auth.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "community_email_verification")
public class EmailVerificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String token;
    private boolean isVerified;

    public EmailVerificationEntity(String email, String token) {
        this.email = email;
        this.token = token;
        this.isVerified = false;
    }

    public void verify() {
        this.isVerified = true;
    }

    public void updateToken(String token) {
        this.token = token;
    }

    public boolean isEqual(String token){
        return this.token.equals(token);
    }
}
