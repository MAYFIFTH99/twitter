package mayfifth99.twitter.acceptance.utils;

import static mayfifth99.twitter.acceptance.steps.LoginAcceptanceSteps.reqLoginGetToken;

import mayfifth99.twitter.auth.application.dto.LoginRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT) // 8080 포트 고정
public class AcceptanceTestTemplate {

    @Autowired
    private DatabaseCleanUp cleanUp;

    @Autowired
    private DataLoader dataLoader;

    @BeforeEach
    public void init(){
        cleanUp.execute();
        dataLoader.loadData();
    }

    protected void cleanUp(){
        cleanUp.execute();
    }
    protected String getEmailToken(String email){
        return dataLoader.getEmailToken(email);
    }

    protected boolean isEmailVerified(String email){
        return dataLoader.isEmailVerified(email);
    }

    protected Long getUserId(String email){
        return dataLoader.getUserId(email);
    }

    protected void createUser(String email){
        dataLoader.createUser(email);
    }

    protected String login(String email){
        return reqLoginGetToken(new LoginRequestDto(email, "password"));
    }

}
