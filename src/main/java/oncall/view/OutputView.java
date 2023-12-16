package oncall.view;

import static oncall.view.OutputView.OutputMessage.DATE_WORKER_FORMAT;
import static oncall.view.OutputView.OutputMessage.HOLIDAY;

import java.time.LocalDate;
import java.util.List;
import oncall.domain.Day;
import oncall.domain.EmergencyWorker;
import oncall.domain.Holiday;

public class OutputView {

    public void printEmergencyWork(List<EmergencyWorker> emergencyWorkers) {
        emergencyWorkers.forEach(worker -> {
            LocalDate localDate = worker.getLocalDate();
            String name = worker.getName();
            String dayOfWeek = Day.of(localDate.getDayOfWeek());
            if (Holiday.isHoliday(localDate)) {
                dayOfWeek += HOLIDAY;
            }
            printForm(localDate, dayOfWeek, name);
        });
    }

    private static void printForm(LocalDate localDate, String dayOfWeek, String name) {
        System.out.println(
                String.format(DATE_WORKER_FORMAT.getMessage(), localDate.getMonth().getValue(),
                        localDate.getDayOfMonth(),
                        dayOfWeek, name));
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
