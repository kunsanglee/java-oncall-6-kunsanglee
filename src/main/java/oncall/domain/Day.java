package oncall.domain;

import static oncall.exception.ExceptionMessage.NOT_FOUND_DAY;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

public enum Day {
    SUNDAY("일", DayOfWeek.SUNDAY),
    MONDAY("월", DayOfWeek.MONDAY),
    TUESDAY("화", DayOfWeek.TUESDAY),
    WEDNESDAY("수", DayOfWeek.WEDNESDAY),
    THURSDAY("목", DayOfWeek.THURSDAY),
    FRIDAY("금", DayOfWeek.FRIDAY),
    SATURDAY("토", DayOfWeek.SATURDAY),
    ;

    private static final Map<String, DayOfWeek> NAME_TO_DAY_OF_WEEK = new HashMap<>();
    private static final Map<DayOfWeek, String> DAY_OF_WEEK_TO_NAME = new HashMap<>();

    static {
        for (Day day : values()) {
            NAME_TO_DAY_OF_WEEK.put(day.name, day.dayOfWeek);
            DAY_OF_WEEK_TO_NAME.put(day.dayOfWeek, day.name);
        }
    }

    private final String name;
    private final DayOfWeek dayOfWeek;

    Day(String name, DayOfWeek dayOfWeek) {
        this.name = name;
        this.dayOfWeek = dayOfWeek;
    }

    public static DayOfWeek of(String name) {
        DayOfWeek dayOfWeek = NAME_TO_DAY_OF_WEEK.get(name);
        if (dayOfWeek == null) {
            throw new IllegalArgumentException(NOT_FOUND_DAY.getMessage());
        }
        return dayOfWeek;
    }

    public static String of(DayOfWeek dayOfWeek) {
        String name = DAY_OF_WEEK_TO_NAME.get(dayOfWeek);
        if (name == null) {
            throw new IllegalArgumentException(NOT_FOUND_DAY.getMessage());
        }
        return name;
    }
}
