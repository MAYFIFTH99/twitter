package mayfifth99.twitter.common;

import java.time.LocalDate;

public class TimeCalculator {

    private TimeCalculator() {
    }

    public static LocalDate getDateDaysAgo(int daysAgo){
        LocalDate currDate = LocalDate.now();
        return currDate.minusDays(daysAgo);
    }

}
