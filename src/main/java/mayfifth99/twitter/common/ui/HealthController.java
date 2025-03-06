package mayfifth99.twitter.common.ui;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health Check")
public class HealthController {

    @GetMapping("/")
    public String healthCheck() {
        return "OK";
    }
}
