package mayfifth99.twitter.acceptance.utils;

import static mayfifth99.twitter.acceptance.steps.SingUpAcceptanceSteps.registerUser;
import static mayfifth99.twitter.acceptance.steps.SingUpAcceptanceSteps.reqSendEmail;
import static mayfifth99.twitter.acceptance.steps.SingUpAcceptanceSteps.reqVerifyEmail;
import static mayfifth99.twitter.acceptance.steps.UserAcceptanceSteps.followUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mayfifth99.twitter.auth.application.dto.CreateUserAuthRequestDto;
import mayfifth99.twitter.auth.application.dto.SendEmailRequestDto;
import mayfifth99.twitter.auth.domain.UserRole;
import mayfifth99.twitter.user.application.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    @PersistenceContext
    private EntityManager em;

    public void loadData(){
        for (int i = 1; i <= 3; i++) {
            createUser("user" + i + "@test.com");
        }

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(1L, 3L));
    }

    public String getEmailToken(String email){
        return em.createNativeQuery("SELECT token FROM community_email_verification WHERE email = ?", String.class)
                .setParameter(1, email)
                .getSingleResult()
                .toString();
    }

    public boolean isEmailVerified(String email){
        return em.createQuery("SELECT e.isVerified FROM EmailVerificationEntity e WHERE email = :email", Boolean.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public Long getUserId(String email){
        return (Long) em.createNativeQuery("SELECT user_id FROM community_user_auth WHERE email = ?", Long.class)
                .setParameter(1, email)
                .getSingleResult();
    }

    public void createUser(String email){
        reqSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        reqVerifyEmail(email, token);
        registerUser(new CreateUserAuthRequestDto(email, "password", UserRole.USER, "name", ""));
    }


}
