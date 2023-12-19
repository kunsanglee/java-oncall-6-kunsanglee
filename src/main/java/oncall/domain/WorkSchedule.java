package oncall.domain;

import java.util.Collections;
import java.util.List;

public class WorkSchedule {
    private final List<WorkDate> schedule;

    public WorkSchedule(List<WorkDate> workDates) {
        this.schedule = workDates;
    }

    public List<WorkDate> getSchedule() {
        return Collections.unmodifiableList(this.schedule);
    }
}
