package oncall;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;
import oncall.domain.EmergencyWorker;
import oncall.domain.Worker;
import oncall.view.InputView;
import oncall.view.OutputView;

public class EmergencyWorkPlanner {
    private final InputView inputView;
    private final OutputView outputView;

    public EmergencyWorkPlanner(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        LocalDate localDate = retry(() -> inputView.readEmergencyWorkMonthDay());
        Worker worker = retry(() -> inputView.readWorker());
        // 입력받은 월, 요일로 시작해서 근무표를 짜야됨.
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        List<EmergencyWorker> emergencyWorkers = worker.makeEmergencyWork(localDate);

        outputView.printEmergencyWork(emergencyWorkers);
    }

    public static <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
