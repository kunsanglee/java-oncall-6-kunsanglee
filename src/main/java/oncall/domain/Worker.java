package oncall.domain;

import java.util.List;

public class Worker {
    private final List<String> weekdayWorkers;
    private final List<String> holidayWorkers;

    public Worker(List<String> weekdayWorkers, List<String> holidayWorkers) {
        this.weekdayWorkers = weekdayWorkers;
        this.holidayWorkers = holidayWorkers;
    }
}
