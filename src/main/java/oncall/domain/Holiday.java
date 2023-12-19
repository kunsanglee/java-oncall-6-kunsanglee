package oncall.domain;

import java.time.LocalDate;
import java.util.Arrays;

public enum Holiday {
    NEW_YEAR(LocalDate.of(2023, 1, 1), "신정"),
    MARCH_FIRST(LocalDate.of(2023, 3, 1), "삼일절"),
    CHILDREN_DAY(LocalDate.of(2023, 5, 5), "어린이날"),
    MEMORIAL_DAY(LocalDate.of(2023, 6, 6), "현충일"),
    LIBERATION_DAY(LocalDate.of(2023, 8, 15), "광복절"),
    TEN_THIRD(LocalDate.of(2023, 10, 3), "개천절"),
    KOREAN_DAY(LocalDate.of(2023, 10, 9), "한글날"),
    CHRISTMAS_DAY(LocalDate.of(2023, 12, 25), "성탄절"),
    ;

    private final LocalDate date;
    private final String name;

    Holiday(LocalDate date, String name) {
        this.date = date;
        this.name = name;
    }

    public static boolean isHoliday(LocalDate localDate) {
        return Arrays.stream(values())
                .anyMatch(holiday -> holiday.date.equals(localDate));
    }
}
