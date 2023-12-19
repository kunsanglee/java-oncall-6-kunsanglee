package oncall.domain;

import static oncall.exception.ExceptionMessage.NOT_FOUND_DAY;

import java.time.DayOfWeek;
import java.util.Arrays;

public enum Day {
    SUNDAY("일", DayOfWeek.SUNDAY),
    MONDAY("월", DayOfWeek.MONDAY),
    TUESDAY("화", DayOfWeek.TUESDAY),
    WEDNESDAY("수", DayOfWeek.WEDNESDAY),
    THURSDAY("목", DayOfWeek.THURSDAY),
    FRIDAY("금", DayOfWeek.FRIDAY),
    SATURDAY("토", DayOfWeek.SATURDAY),
    ;

    private final String name;
    private final DayOfWeek dayOfWeek;

    Day(String name, DayOfWeek dayOfWeek) {
        this.name = name;
        this.dayOfWeek = dayOfWeek;
    }

    public static DayOfWeek of(String name) {
        return Arrays.stream(values())
                .filter(day -> day.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_DAY.getMessage()))
                .dayOfWeek;
    }

    public static String of(DayOfWeek dayOfWeek) {
        return Arrays.stream(values())
                .filter(day -> day.dayOfWeek.equals(dayOfWeek))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_DAY.getMessage()))
                .name;
    }
}
