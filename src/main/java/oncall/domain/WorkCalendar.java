package oncall.domain;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class WorkCalendar {
    private final Month month;
    private final DayOfWeek dayOfWeek;
    private final DayOfMonth lastDateOfMonth;
    private final DayOfMonth monthOfHoliday;

    public WorkCalendar(Month month, DayOfWeek dayOfWeek) {
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        this.lastDateOfMonth = DayOfMonth.lastDateOf(month);
        this.monthOfHoliday = DayOfMonth.getMonthOfHoliday(month);
    }

    public WorkSchedule makeWorkSchedule(Workers workers) {
        DayOfMonth day = DayOfMonth.of(month, 1);
        return new WorkSchedule(addWorker(workers, day)); // 근무 순번 리스트를 넣어서 생성
    }

    private List<WorkDate> addWorker(Workers workers, DayOfMonth day) {
        List<WorkDate> schedule = new ArrayList<>();
        DayOfWeek currentDayOfWeek = dayOfWeek;
        while (day.isBeforeOrEqual(lastDateOfMonth)) {
            // 평일과 휴일을 판단하여 적절한 사원을 꺼내서 직전 투입한 사원과 동일한지 비교한다.
            boolean isHoliday = day.isSame(monthOfHoliday);
            schedule.add(getWorkDate(workers, day, currentDayOfWeek, isHoliday, schedule));
            workers.increaseIndex(isHoliday);
            day.increaseDate();
            currentDayOfWeek = currentDayOfWeek.plus(1);
        }
        return schedule;
    }

    private WorkDate getWorkDate(Workers workers, DayOfMonth day, DayOfWeek currentDayOfWeek, boolean isHoliday,
                                 List<WorkDate> schedule) {
        Worker worker = workers.getWorker(currentDayOfWeek, isHoliday);
        if (!schedule.isEmpty() && schedule.get(schedule.size() - 1).isSameWorker(worker)) {
            Worker changedWorker = workers.getChangedWorker(isHoliday);
            return new WorkDate(month, day, currentDayOfWeek, changedWorker);
        }
        return new WorkDate(month, day, currentDayOfWeek, worker);
    }
}
