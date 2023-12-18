package oncall.domain;

import java.util.Objects;

public class Worker {
    private final String name;

    public Worker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Worker worker)) {
            return false;
        }
        return Objects.equals(name, worker.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
