package oncall.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Worker {
    private final List<String> weekdayWorkers;
    private final List<String> holidayWorkers;

    public Worker(List<String> weekdayWorkers, List<String> holidayWorkers) {
        this.weekdayWorkers = weekdayWorkers;
        this.holidayWorkers = holidayWorkers;
    }

    public List<EmergencyWorker> makeEmergencyWork(LocalDate date) {
        LocalDate lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        List<EmergencyWorker> emergencyWorkers = new ArrayList<>();
        System.out.println(date.getDayOfMonth());
        int weekdayIndex = 0;
        int holidayIndex = 0;
        while (date.isBefore(lastDayOfMonth) || date.isEqual(lastDayOfMonth)) { // 마지막날까지 반복
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            int size = emergencyWorkers.size();
            if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) {
                // 주말인원 투입
                String worker = holidayWorkers.get(holidayIndex);
                // 그전날 인원과 같으면 다음 주말 사원과 바꾼다.
                if (size != 0 && emergencyWorkers.get(size - 1).isSame(worker)) {
                    int index = (holidayIndex + 1) % holidayWorkers.size();
                    String nextWorker = holidayWorkers.get(index);
                    holidayWorkers.set(holidayIndex, nextWorker); // 순서를 아예 바꾸는게 맞을지
                    holidayWorkers.set(index, worker);
                    emergencyWorkers.add(new EmergencyWorker(nextWorker, date));
                    date = date.plusDays(1);
                    holidayIndex = (holidayIndex + 1) % holidayWorkers.size();
                    continue;
                }
                emergencyWorkers.add(new EmergencyWorker(worker, date));
                date = date.plusDays(1);
                holidayIndex = (holidayIndex + 1) % holidayWorkers.size();
                continue;
            }
            // 평일 투입
            // 그전날 사원과 동일인물이면 다음 사원과 자리 바꾼다.
            String worker = weekdayWorkers.get(weekdayIndex);
            // 그전날 인원과 같으면 다음 주말 사원과 바꾼다.
            if (size != 0 && emergencyWorkers.get(size - 1).isSame(worker)) {
                int index = (weekdayIndex + 1) % holidayWorkers.size();
                String nextWorker = weekdayWorkers.get(index);
                weekdayWorkers.set(weekdayIndex, nextWorker); // 순서를 아예 바꾸는게 맞을지
                weekdayWorkers.set(index, worker);
                emergencyWorkers.add(new EmergencyWorker(nextWorker, date));
                weekdayIndex = (weekdayIndex + 1) % weekdayWorkers.size();
                date = date.plusDays(1);
                continue;
            }
            emergencyWorkers.add(new EmergencyWorker(worker, date));
            weekdayIndex = (weekdayIndex + 1) % weekdayWorkers.size();
            date = date.plusDays(1);
        }
        return emergencyWorkers;
    }
}
