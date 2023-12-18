package oncall.domain;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class WorkCalendar {
    private final Month month;
    private final DayOfWeek startDayOfWeek;
    private final int lastDateOfMonth;
    private final List<WorkDate> calendar = new ArrayList<>();

    public WorkCalendar(Month month, DayOfWeek startDayOfWeek) {
        this.month = month;
        this.startDayOfWeek = startDayOfWeek;
        this.lastDateOfMonth = getLastDateOfMonth(month);
    }

    private static int getLastDateOfMonth(Month month) {
        if (month.equals(Month.FEBRUARY)) {
            return 28;
        }
        return month.maxLength();
    }
}
