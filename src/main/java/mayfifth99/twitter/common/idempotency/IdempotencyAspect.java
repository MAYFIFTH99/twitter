package mayfifth99.twitter.common.idempotency;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import mayfifth99.twitter.common.ui.Response;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class IdempotencyAspect {

    private final IdempotencyRepository idempotencyRepository;
    private final HttpServletRequest request;

    @Around("@annotation(Idempotent)")
    public Object checkIdempotency(ProceedingJoinPoint jointPoint) throws Throwable {
        String idempotencyKey = request.getHeader("Idempotency-Key");
        if(idempotencyKey == null){
            return jointPoint.proceed();
        }

        Idempotency idempotency = idempotencyRepository.getByKey(idempotencyKey);
        if(idempotency != null){
            return idempotency.getResponse(); // 로직을 수행하지 않고 응답 반환
        }

        Object result = jointPoint.proceed(); // 대상 메서드(@Idempontent) 실행
        Idempotency newIdempotency = new Idempotency(idempotencyKey, (Response<?>) result);
        idempotencyRepository.save(newIdempotency);

        return result;
    }
}
