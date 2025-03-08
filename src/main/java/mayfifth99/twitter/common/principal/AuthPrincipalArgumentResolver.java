package mayfifth99.twitter.common.principal;

import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.auth.domain.TokenProvider;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
public class AuthPrincipalArgumentResolver implements HandlerMethodArgumentResolver {

    private final TokenProvider tokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        try{
            String authToken = webRequest.getHeader("Authorization");
            if(authToken == null || authToken.split(" ").length != 2){
                throw new IllegalArgumentException("토큰이 없거나 올바르지 않은 형식입니다.");
            }
            String token = authToken.split(" ")[1];
            Long userId = tokenProvider.getUserId(token);
            String role = tokenProvider.role(token);

            return new UserPrincipal(userId, role);

        }catch (Exception e){
            throw new IllegalArgumentException("올바르지 않은 토큰입니다.");
        }
    }
}
