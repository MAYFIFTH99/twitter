package mayfifth99.twitter.auth.domain;

import lombok.Getter;

@Getter
public class Password {

    private final String encryptedPassword;

    private Password(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public static Password createEncryptPassword(String password){
        if(password == null || password.isBlank()){
            throw new IllegalArgumentException("Password is empty");
        }

        String encryptedPassword = SHA256.encrypt(password);
        return new Password(encryptedPassword);
    }

    public boolean matchPassword(String password){
        return encryptedPassword.matches(SHA256.encrypt(password));
    }
}
