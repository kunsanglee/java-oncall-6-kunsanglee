package oncall.view;

import static oncall.view.OutputView.OutputMessage.DATE_WORKER_FORMAT;
import static oncall.view.OutputView.OutputMessage.HOLIDAY;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import oncall.domain.Day;
import oncall.domain.EmergencyWorker;
import oncall.domain.Holiday;

public class OutputView {

    public void printEmergencyWork(List<EmergencyWorker> emergencyWorkers) {
        emergencyWorkers.forEach(worker -> {
            LocalDate localDate = worker.getLocalDate();
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            String name = worker.getName();
            if (!(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) && Holiday.isHoliday(localDate)) {
                printHolidayForm(localDate, Day.of(localDate.getDayOfWeek()) + HOLIDAY.getMessage(), name);
            }
            printDefaultForm(localDate, name);
        });
    }

    private static void printHolidayForm(LocalDate localDate, String localDate1, String name) {
        System.out.println(
                String.format(DATE_WORKER_FORMAT.getMessage(), localDate.getMonth().getValue(),
                        localDate.getDayOfMonth(),
                        localDate1, name));
    }

    private static void printDefaultForm(LocalDate localDate, String name) {
        printHolidayForm(localDate, Day.of(localDate.getDayOfWeek()), name);
    }

    protected enum OutputMessage {
        DATE_WORKER_FORMAT("%d월 %d일 %s %s"),
        HOLIDAY("(휴일)"),
        ;

        private final String message;

        OutputMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
