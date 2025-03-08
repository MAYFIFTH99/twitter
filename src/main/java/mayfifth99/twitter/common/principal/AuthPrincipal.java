package mayfifth99.twitter.common.principal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @AuthPrincipal 어노테이션이 있으면 헤더에서 인증 토큰을 가져온 후, 파싱하는 기능을 추가
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthPrincipal {

}
