package oncall.domain;

import static oncall.exception.ExceptionMessage.INVALID_DAY_INPUT;

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

    public static Day of(String name) {
        return Arrays.stream(values())
                .filter(day -> day.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_DAY_INPUT.getMessage()));
    }

    public static String of(DayOfWeek dayOfWeek) {
        return Arrays.stream(values())
                .filter(day -> day.dayOfWeek.equals(dayOfWeek))
                .findFirst()
                .map(day -> day.name)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_DAY_INPUT.getMessage()));
    }

    public boolean isSame(DayOfWeek dayOfWeek) {
        return this.dayOfWeek.equals(dayOfWeek);
    }
}
