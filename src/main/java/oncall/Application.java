package oncall;

import oncall.domain.WorkScheduler;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {
    public static void main(String[] args) {
        WorkScheduler workScheduler = new WorkScheduler(new InputView(), new OutputView());
        workScheduler.run();
    }
}
