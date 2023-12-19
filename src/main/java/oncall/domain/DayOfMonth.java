package oncall.domain;

import static oncall.exception.ExceptionMessage.INVALID_DATE;

import java.time.Month;

public class DayOfMonth {
    public static final DayOfMonth NONE = new DayOfMonth(0);
    private final int date;

    public DayOfMonth(int date) {
        this.date = date;
    }

    public static DayOfMonth of(Month month, int date) {
        int lastDateOfMonth = getLastDateOfMonth(month);
        if (date < 1 || lastDateOfMonth < date) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
        return new DayOfMonth(date);
    }

    public static DayOfMonth lastDateOf(Month month) {
        return new DayOfMonth(getLastDateOfMonth(month));
    }

    private static int getLastDateOfMonth(Month month) {
        if (month.equals(Month.FEBRUARY)) {
            return 28;
        }
        return month.maxLength();
    }

    public static DayOfMonth getMonthOfHoliday(Month month) {
        int holiday = Holiday.of(month);
        if (holiday == 0) {
            return DayOfMonth.NONE;
        }
        return DayOfMonth.of(month, holiday);
    }

    public DayOfMonth increaseDate() {
        return new DayOfMonth(this.date + 1);
    }

    public boolean isSame(DayOfMonth date) {
        return this.date == date.date;
    }

    public boolean isBeforeOrEqual(DayOfMonth date) {
        return this.date <= date.date;
    }

    public int getValue() {
        return this.date;
    }
}
