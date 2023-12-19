package oncall.domain;

import java.time.Month;
import java.util.Arrays;

public enum Holiday {
    NEW_YEAR(Month.JANUARY, 1),
    INDEPENDENCE_DAY(Month.MARCH, 1),
    CHILDREN_DAY(Month.MAY, 5),
    MEMORIAL_DAY(Month.JUNE, 6),
    LIBERATION_DAY(Month.AUGUST, 15),
    FOUNDATION_DAY(Month.OCTOBER, 3),
    KOREAN_DAY(Month.OCTOBER, 9),
    CHRISTMAS_DAY(Month.DECEMBER, 25),
    ;

    private final Month month;
    private final int date;

    Holiday(Month month, int date) {
        this.month = month;
        this.date = date;
    }

    public static int of(Month month) {
        return Arrays.stream(values())
                .filter(holiday -> holiday.month.equals(month))
                .findAny()
                .map(holiday -> holiday.date)
                .orElse(0);
    }
}
