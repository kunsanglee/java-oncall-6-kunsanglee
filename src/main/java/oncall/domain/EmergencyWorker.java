package oncall.domain;

import java.time.LocalDate;

public class EmergencyWorker {
    private final String name;
    private final LocalDate localDate;

    public EmergencyWorker(String name, LocalDate localDate) {
        this.name = name;
        this.localDate = localDate;
    }

    public boolean isSame(String name) {
        return this.name.equals(name);
    }

    public String getName() {
        return name;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }
}
