package oncall.view;

import oncall.domain.Day;
import oncall.domain.WorkSchedule;

public class OutputView {

    public void printWorkSchedule(WorkSchedule workSchedule) {
        workSchedule.getSchedule()
                .forEach(workDate -> System.out.println(String.format("%d월 %d일 %s요일 %s", workDate.getMonth().getValue(),
                        workDate.getDayOfMonth().getValue(), Day.of(workDate.getDayOfWeek()),
                        workDate.getWorker().getName())));
    }
}
