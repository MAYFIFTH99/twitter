package mayfifth99.twitter.common.config.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/{key}/{value}")
    public String stringsSet(@PathVariable String key, @PathVariable String value){
        final ValueOperations<String,String> stringStringValueOperations = redisTemplate.opsForValue();

        stringStringValueOperations.set(key,value); // redis set 명령어 (SET key value)
        System.out.println("stringStringValueOperations's key = " + stringStringValueOperations.get(key));
        return "ok";
    }

}
