package oncall.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Worker {
    private static final int INCREASE_DATE = 1;
    private static final int OFFSET = 1;
    private final List<String> weekdayWorkers;
    private final List<String> holidayWorkers;

    public Worker(List<String> weekdayWorkers, List<String> holidayWorkers) {
        this.weekdayWorkers = weekdayWorkers;
        this.holidayWorkers = holidayWorkers;
    }

    public List<EmergencyWorker> makeEmergencyWork(LocalDate date) {
        LocalDate lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        List<EmergencyWorker> emergencyWorkers = new ArrayList<>();
        int weekdayIndex = 0;
        int holidayIndex = 0;
        while (!date.isAfter(lastDayOfMonth)) {
            if (isWeekend(date) || Holiday.isHoliday(date)) {
                holidayIndex = assignWorkerAndAdjustIndex(emergencyWorkers, holidayWorkers, holidayIndex, date);
                date = date.plusDays(INCREASE_DATE);
                continue;
            }
            weekdayIndex = assignWorkerAndAdjustIndex(emergencyWorkers, weekdayWorkers, weekdayIndex, date);
            date = date.plusDays(INCREASE_DATE);
        }
        return emergencyWorkers;
    }

    private boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY);
    }

    private int assignWorkerAndAdjustIndex(List<EmergencyWorker> emergencyWorkers, List<String> workers, int index,
                                           LocalDate date) {
        String worker = workers.get(index);
        if (!emergencyWorkers.isEmpty() && emergencyWorkers.get(emergencyWorkers.size() - OFFSET).isSame(worker)) {
            index = (index + OFFSET) % workers.size();
            worker = workers.get(index);
        }
        emergencyWorkers.add(new EmergencyWorker(worker, date));
        return (index + OFFSET) % workers.size();
    }
}

