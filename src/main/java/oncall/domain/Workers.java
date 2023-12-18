package oncall.domain;

import static oncall.exception.ExceptionMessage.DUPLICATED_WORKER;
import static oncall.exception.ExceptionMessage.INVALID_WORKER_NAME_CHARACTER;
import static oncall.exception.ExceptionMessage.INVALID_WORKER_NAME_LENGTH;
import static oncall.exception.ExceptionMessage.INVALID_WORKER_SIZE;
import static oncall.exception.ExceptionMessage.NOT_CONTAINS_ALL_EACH_WORKER;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Workers {
    private final List<Worker> weekdayWorkers;
    private final List<Worker> holidayWorkers;

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
        if (workers.size() < 5 || 35 < workers.size()) {
            throw new IllegalArgumentException(INVALID_WORKER_SIZE.getMessage());
        }
        if (isInvalidName(workers)) {
            throw new IllegalArgumentException(INVALID_WORKER_NAME_CHARACTER.getMessage());
        }
        if (workers.stream().anyMatch(worker -> 5 < worker.length())) {
            throw new IllegalArgumentException(INVALID_WORKER_NAME_LENGTH.getMessage());
        }
    }

    private static boolean isInvalidName(List<String> workers) {
        return workers.stream()
                .anyMatch(worker -> worker.chars().anyMatch(character -> character < '가' || '힣' < character));
    }

    private static boolean isDuplicated(List<String> workers) {
        return workers.stream()
                .distinct()
                .count() != workers.size();
    }
}
