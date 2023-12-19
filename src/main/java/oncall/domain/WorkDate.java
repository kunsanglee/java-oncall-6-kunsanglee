package oncall.domain;

import java.time.DayOfWeek;
import java.time.Month;

public class WorkDate {
    private final Month month;
    private final DayOfMonth dayOfMonth;
    private final DayOfWeek dayOfWeek;
    private final Worker worker;

    public WorkDate(Month month, DayOfMonth dayOfMonth, DayOfWeek dayOfWeek, Worker worker) {
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.dayOfWeek = dayOfWeek;
        this.worker = worker;
    }

    public boolean isSameWorker(Worker worker) {
        return this.worker.equals(worker);
    }

    public Month getMonth() {
        return month;
    }

    public DayOfMonth getDayOfMonth() {
        return dayOfMonth;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Worker getWorker() {
        return worker;
    }
}
