package mayfifth99.twitter.common.idempotency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mayfifth99.twitter.common.ui.Response;

@Getter
@AllArgsConstructor
public class Idempotency {

    private final String key;
    private final Response<?> response;
}
