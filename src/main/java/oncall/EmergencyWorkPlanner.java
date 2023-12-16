package oncall;

import static oncall.util.Retry.retry;

import java.time.LocalDate;
import java.util.List;
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
        LocalDate localDate = retry(inputView::readEmergencyWorkMonthDay);
        Worker worker = retry(inputView::readWorker);
        List<EmergencyWorker> emergencyWorkers = worker.makeEmergencyWork(localDate);
        outputView.printEmergencyWork(emergencyWorkers);
    }
}
