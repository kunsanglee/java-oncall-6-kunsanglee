package oncall.domain;

import static oncall.util.Retry.retry;

import oncall.view.InputView;
import oncall.view.OutputView;

public class WorkScheduler {
    private final InputView inputView;
    private final OutputView outputView;

    public WorkScheduler(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        WorkCalendar workCalendar = retry(inputView::readWorkCalendar);
    }
}
