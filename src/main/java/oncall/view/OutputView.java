package oncall.view;

import java.time.DayOfWeek;
import oncall.domain.Day;
import oncall.domain.DayOfMonth;
import oncall.domain.Holiday;
import oncall.domain.WorkDate;
import oncall.domain.WorkSchedule;

public class OutputView {
    private static final String HOLIDAY = "(휴일)";
    private static final String SCHEDULE_FORMAT = "%d월 %d일 %s %s";

    public void printWorkSchedule(WorkSchedule workSchedule) {
        workSchedule.getSchedule().forEach(workDate -> {
            String day = Day.of(workDate.getDayOfWeek());
            if (isHoliday(workDate)) {
                day += HOLIDAY;
            }
            System.out.println(String.format(SCHEDULE_FORMAT, workDate.getMonth().getValue(),
                    workDate.getDayOfMonth().getValue(), day, workDate.getWorker().getName()));
        });
    }

    private boolean isHoliday(WorkDate workDate) {
        DayOfMonth holiday = Holiday.of(workDate.getMonth());
        DayOfWeek dayOfWeek = workDate.getDayOfWeek();
        return !dayOfWeek.equals(DayOfWeek.SATURDAY) && !dayOfWeek.equals(DayOfWeek.SUNDAY)
                && holiday.isSame(workDate.getDayOfMonth());
    }
}
