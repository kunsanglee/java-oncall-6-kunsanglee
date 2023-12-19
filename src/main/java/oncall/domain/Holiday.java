package oncall.domain;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

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

    private static final Map<Month, DayOfMonth> MONTH_TO_HOLIDAY = new HashMap<>();

    static {
        for (Holiday holiday : values()) {
            MONTH_TO_HOLIDAY.put(holiday.month, holiday.day);
        }
    }

    private final Month month;
    private final DayOfMonth day;

    Holiday(Month month, int day) {
        this.month = month;
        this.day = DayOfMonth.of(month, day);
    }

    public static DayOfMonth of(Month month) {
        return MONTH_TO_HOLIDAY.getOrDefault(month, DayOfMonth.NONE);
    }
}
