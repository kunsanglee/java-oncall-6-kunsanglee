package oncall.domain;

import static oncall.exception.ExceptionMessage.DUPLICATED_WORKER;
import static oncall.exception.ExceptionMessage.INVALID_WORKER_NAME_CHARACTER;
import static oncall.exception.ExceptionMessage.INVALID_WORKER_NAME_LENGTH;
import static oncall.exception.ExceptionMessage.INVALID_WORKER_SIZE;
import static oncall.exception.ExceptionMessage.NOT_CONTAINS_ALL_EACH_WORKER;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Workers {
    private static final int MIN_WORKER_SIZE = 5;
    private static final int MAX_WORKER_SIZE = 35;
    private static final int MIN_WORKER_NAME_LENGTH = 2;
    private static final int MAX_WORKER_NAME_LENGTH = 5;
    private static final char MIN_KOREAN_CHARACTER = '가';
    private static final char MAX_KOREAN_CHARACTER = '힣';
    private static final int FOR_NEXT_INDEX = 1;
    private final List<Worker> weekdayWorkers;
    private final List<Worker> holidayWorkers;
    private int weekdayWorkerIndex = 0;
    private int holidayWorkerIndex = 0;

    public Workers(List<Worker> weekdayWorkers, List<Worker> holidayWorkers) {
        validateContainsEachWorker(weekdayWorkers, holidayWorkers);
        this.weekdayWorkers = weekdayWorkers;
        this.holidayWorkers = holidayWorkers;
    }

    private static void validateContainsEachWorker(List<Worker> weekdayWorkers, List<Worker> holidayWorkers) {
        HashSet<Worker> weekday = new HashSet<>(weekdayWorkers);
        HashSet<Worker> holiday = new HashSet<>(holidayWorkers);
        if (!weekday.containsAll(holidayWorkers) || !holiday.containsAll(weekdayWorkers)) {
            throw new IllegalArgumentException(NOT_CONTAINS_ALL_EACH_WORKER.getMessage());
        }
    }

    public static Workers of(List<String> weekdayWorkers, List<String> holidayWorkers) {
        return new Workers(convertTo(weekdayWorkers), convertTo(holidayWorkers));
    }

    private static List<Worker> convertTo(List<String> workers) {
        validate(workers);
        return workers.stream()
                .map(Worker::new)
                .collect(Collectors.toList());
    }

    private static void validate(List<String> workers) {
        if (isDuplicated(workers)) {
            throw new IllegalArgumentException(DUPLICATED_WORKER.getMessage());
        }
        if (isInvalidWorkerSize(workers)) {
            throw new IllegalArgumentException(INVALID_WORKER_SIZE.getMessage());
        }
        if (isInvalidName(workers)) {
            throw new IllegalArgumentException(INVALID_WORKER_NAME_CHARACTER.getMessage());
        }
        if (isInvalidNameLength(workers)) {
            throw new IllegalArgumentException(INVALID_WORKER_NAME_LENGTH.getMessage());
        }
    }

    private static boolean isDuplicated(List<String> workers) {
        return workers.stream()
                .distinct()
                .count() != workers.size();
    }

    private static boolean isInvalidWorkerSize(List<String> workers) {
        return workers.size() < MIN_WORKER_SIZE || MAX_WORKER_SIZE < workers.size();
    }

    private static boolean isInvalidName(List<String> workers) {
        return workers.stream()
                .anyMatch(worker -> worker.chars()
                        .anyMatch(character -> character < MIN_KOREAN_CHARACTER || MAX_KOREAN_CHARACTER
                                < character));
    }

    private static boolean isInvalidNameLength(List<String> workers) {
        return workers.stream().anyMatch(
                worker -> worker.length() < MIN_WORKER_NAME_LENGTH || MAX_WORKER_NAME_LENGTH < worker.length());
    }

    public Worker getWorker(DayOfWeek dayOfWeek, boolean isHoliday) {
        if (isHoliday(dayOfWeek, isHoliday)) {
            return this.holidayWorkers.get(holidayWorkerIndex);
        }
        return this.weekdayWorkers.get(weekdayWorkerIndex);
    }

    private static boolean isHoliday(DayOfWeek dayOfWeek, boolean isHoliday) {
        return dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY) || isHoliday;
    }

    public Worker getChangedWorker(DayOfWeek dayOfWeek, boolean isHoliday) {
        if (isHoliday(dayOfWeek, isHoliday)) {
            changeWorkerSequence(this.holidayWorkers, holidayWorkerIndex);
            return this.holidayWorkers.get(holidayWorkerIndex);
        }
        changeWorkerSequence(this.weekdayWorkers, weekdayWorkerIndex);
        return this.weekdayWorkers.get(weekdayWorkerIndex);
    }

    private void changeWorkerSequence(List<Worker> workers, int index) {
        Worker currentWorker = workers.get(index);
        int nextIndex = (index + FOR_NEXT_INDEX) % workers.size();
        Worker nextWorker = workers.get(nextIndex);
        workers.set(index, nextWorker);
        workers.set(nextIndex, currentWorker);
    }

    public void increaseIndex(DayOfWeek dayOfWeek, boolean isHoliday) {
        if (isHoliday(dayOfWeek, isHoliday)) {
            holidayWorkerIndex = (holidayWorkerIndex + FOR_NEXT_INDEX) % this.holidayWorkers.size();
            return;
        }
        weekdayWorkerIndex = (weekdayWorkerIndex + FOR_NEXT_INDEX) % this.weekdayWorkers.size();
    }
}
