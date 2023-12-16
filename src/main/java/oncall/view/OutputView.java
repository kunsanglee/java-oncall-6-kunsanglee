package oncall.view;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import oncall.domain.Day;
import oncall.domain.EmergencyWorker;
import oncall.domain.Holiday;

public class OutputView {
    private static final String HOLIDAY = "(휴일)";
    private static final String DATE_FORMAT = "%d월 %d일 %s %s";

    public void printEmergencyWork(List<EmergencyWorker> emergencyWorkers) {
        emergencyWorkers.forEach(worker -> {
            LocalDate localDate = worker.getLocalDate();
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            String name = worker.getName();
            if (!(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) && Holiday.isHoliday(localDate)) {
                printHolidayForm(localDate, Day.of(localDate.getDayOfWeek()) + HOLIDAY, name);
            }
            printDefaultForm(localDate, name);
        });
    }

    private static void printHolidayForm(LocalDate localDate, String localDate1, String name) {
        System.out.println(
                String.format(DATE_FORMAT, localDate.getMonth().getValue(), localDate.getDayOfMonth(),
                        localDate1, name));
    }

    private static void printDefaultForm(LocalDate localDate, String name) {
        printHolidayForm(localDate, Day.of(localDate.getDayOfWeek()), name);
    }
}
