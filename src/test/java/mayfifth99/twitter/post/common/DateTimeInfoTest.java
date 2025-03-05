package mayfifth99.twitter.post.common;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DateTimeInfoTest {

    @Test
    void givenCreated_whenUpdated_thenTimeAndStateArgsUpdated() throws Exception {
        //given
        DateTimeInfo dateTimeInfo = new DateTimeInfo();
        LocalDateTime beforeUpdate = dateTimeInfo.getDateTime();

        Thread.sleep(1);
        //when
        dateTimeInfo.update();

        //then
        assertTrue(dateTimeInfo.isEdited());
        assertNotEquals(beforeUpdate, dateTimeInfo.getDateTime());
    }
}