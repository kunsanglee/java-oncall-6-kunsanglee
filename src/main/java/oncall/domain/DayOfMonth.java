package oncall.domain;

import static oncall.exception.ExceptionMessage.INVALID_DATE;

import java.time.Month;

public class DayOfMonth {
    public static final DayOfMonth NONE = new DayOfMonth(0);
    private static final int MIN_DATE = 1;
    private static final int FEBRUARY_MAX_DAY = 28;
    private final int date;

    public DayOfMonth(int date) {
        this.date = date;
    }

    public static DayOfMonth of(Month month, int date) {
        int lastDayOfMonth = getLastDayOfMonth(month);
        if (date < MIN_DATE || lastDayOfMonth < date) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
        return new DayOfMonth(date);
    }

    public static DayOfMonth lastDayOf(Month month) {
        return new DayOfMonth(getLastDayOfMonth(month));
    }

    private static int getLastDayOfMonth(Month month) {
        if (month.equals(Month.FEBRUARY)) {
            return FEBRUARY_MAX_DAY;
        }
        return month.maxLength();
    }

    public static DayOfMonth getHolidayOfMonth(Month month) {
        return Holiday.of(month);
    }

    public DayOfMonth increaseDay() {
        return new DayOfMonth(this.date + MIN_DATE);
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
