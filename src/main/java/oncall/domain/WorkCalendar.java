package oncall.domain;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class WorkCalendar {
    private static final int FIRST_DAY_OF_MONTH = 1;
    private static final int ONE = 1;
    private final Month month;
    private final DayOfWeek dayOfWeek;
    private final DayOfMonth lastDateOfMonth;
    private final DayOfMonth monthOfHoliday;

    public WorkCalendar(Month month, DayOfWeek dayOfWeek) {
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        this.lastDateOfMonth = DayOfMonth.lastDayOf(month);
        this.monthOfHoliday = DayOfMonth.getHolidayOfMonth(month);
    }

    public WorkSchedule makeWorkSchedule(Workers workers) {
        DayOfMonth day = DayOfMonth.of(month, FIRST_DAY_OF_MONTH);
        return new WorkSchedule(addWorker(workers, day));
    }

    private List<WorkDate> addWorker(Workers workers, DayOfMonth day) {
        List<WorkDate> schedule = new ArrayList<>();
        DayOfWeek currentDayOfWeek = dayOfWeek;
        while (day.isBeforeOrEqual(lastDateOfMonth)) {
            boolean isHoliday = day.isSame(monthOfHoliday);
            schedule.add(getWorkDate(workers, day, currentDayOfWeek, isHoliday, schedule));
            workers.increaseIndex(currentDayOfWeek, isHoliday);
            day = day.increaseDay();
            currentDayOfWeek = currentDayOfWeek.plus(ONE);
        }
        return schedule;
    }

    private WorkDate getWorkDate(Workers workers, DayOfMonth day, DayOfWeek currentDayOfWeek, boolean isHoliday,
                                 List<WorkDate> schedule) {
        Worker worker = workers.getWorker(currentDayOfWeek, isHoliday);
        int lastIndex = schedule.size() - ONE;
        if (!schedule.isEmpty() && schedule.get(lastIndex).isSameWorker(worker)) {
            Worker changedWorker = workers.getChangedWorker(currentDayOfWeek, isHoliday);
            return new WorkDate(month, day, currentDayOfWeek, changedWorker);
        }
        return new WorkDate(month, day, currentDayOfWeek, worker);
    }
}
