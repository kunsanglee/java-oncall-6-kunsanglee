package oncall;

import java.util.function.Supplier;
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
        int month = retry(() -> inputView.readEmergencyWorkMonthDay());
        System.out.println(month);

        retry(() -> inputView.readWorker());
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
