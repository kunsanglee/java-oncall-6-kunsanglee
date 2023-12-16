package oncall;

import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {
    public static void main(String[] args) {
        EmergencyWorkPlanner emergencyWorkPlanner = new EmergencyWorkPlanner(new InputView(), new OutputView());
        emergencyWorkPlanner.run();
    }
}
