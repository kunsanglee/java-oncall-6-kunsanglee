package oncall.domain;

import java.util.Collections;
import java.util.List;

public class EmergencyWorkers {
    private final List<EmergencyWorker> emergencyWorkers;

    public EmergencyWorkers(List<EmergencyWorker> emergencyWorkers) {
        this.emergencyWorkers = emergencyWorkers;
    }

    public List<EmergencyWorker> getEmergencyWorkers() {
        return Collections.unmodifiableList(emergencyWorkers);
    }
}
