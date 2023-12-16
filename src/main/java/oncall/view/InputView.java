package oncall.view;

import static oncall.exception.ExceptionMessage.DUPLICATED_NICKNAME;
import static oncall.exception.ExceptionMessage.INVALID_DAY_OF_MONTH;
import static oncall.exception.ExceptionMessage.INVALID_MONTH_DAY_INPUT;
import static oncall.exception.ExceptionMessage.INVALID_MONTH_INPUT;
import static oncall.exception.ExceptionMessage.INVALID_NICKNAME_CHARACTER;
import static oncall.exception.ExceptionMessage.INVALID_NICKNAME_LENGTH;
import static oncall.exception.ExceptionMessage.INVALID_WORKER_COUNTS;
import static oncall.exception.ExceptionMessage.WORKER_NOT_MATCHED;
import static oncall.view.InputView.InputMessage.INPUT_MONTH_DAY;
import static oncall.view.InputView.InputMessage.INPUT_WEEKDAY_EMERGENCY_WORKERS;
import static oncall.view.InputView.InputMessage.INPUT_WEEKEND_EMERGENCY_WORKERS;

import camp.nextstep.edu.missionutils.Console;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import oncall.domain.Day;
import oncall.domain.Worker;

public class InputView {
    private static final String COMMA = ",";
    private static final int DEFAULT_YEAR = 2023;
    private static final int DEFAULT_FIRST_DAY = 1;
    private static final int MONTH_DAY_INPUT_EXACT_SIZE = 2;
    private static final int MONTH_INDEX = 0;
    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;
    private static final int MIN_WORKER_SIZE = 5;
    private static final int MAX_WORKER_SIZE = 35;
    private static final int MIN_WORKER_NICKNAME_LENGTH = 2;
    private static final int MAX_WORKER_NICKNAME_LENGTH = 5;
    private static final char MIN_KOREAN_CHARACTER = '가';
    private static final char MAX_KOREAN_CHARACTER = '힣';

    public LocalDate readEmergencyWorkMonthDay() {
        System.out.print(INPUT_MONTH_DAY.getMessage());
        List<String> parsedMonthDay = parseByComma(Console.readLine());
        validateMonthDayForm(parsedMonthDay);
        int month = parseMonth(parsedMonthDay);
        validateMonth(month);
        Day day = Day.of(parsedMonthDay.get(DEFAULT_FIRST_DAY));
        return validateDay(month, day);
    }

    private static List<String> parseByComma(String input) {
        return Arrays.asList(input.split(COMMA));
    }

    private static LocalDate validateDay(int month, Day day) {
        LocalDate localDate = LocalDate.of(DEFAULT_YEAR, month, DEFAULT_FIRST_DAY);
        validateDayOfMonth(day, localDate);
        return localDate;
    }

    private static void validateMonthDayForm(List<String> parsedMonthDay) {
        if (parsedMonthDay.size() != MONTH_DAY_INPUT_EXACT_SIZE) {
            throw new IllegalArgumentException(INVALID_MONTH_DAY_INPUT.getMessage());
        }
    }

    private static int parseMonth(List<String> parsedMonthDay) {
        try {
            return Integer.parseInt(parsedMonthDay.get(MONTH_INDEX));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_MONTH_INPUT.getMessage());
        }
    }

    private static void validateMonth(int month) {
        if (month < MIN_MONTH || MAX_MONTH < month) {
            throw new IllegalArgumentException(INVALID_MONTH_INPUT.getMessage());
        }
    }

    private static void validateDayOfMonth(Day day, LocalDate localDate) {
        if (!day.isSame(localDate.getDayOfWeek())) {
            throw new IllegalArgumentException(INVALID_DAY_OF_MONTH.getMessage());
        }
    }

    public Worker readWorker() {
        System.out.print(INPUT_WEEKDAY_EMERGENCY_WORKERS.getMessage());
        List<String> weekdayWorkers = getWorkers();
        System.out.print(INPUT_WEEKEND_EMERGENCY_WORKERS.getMessage());
        List<String> holidayWorkers = getWorkers();
        validateWeekdayAndHolidayWorkers(weekdayWorkers, holidayWorkers);
        return new Worker(weekdayWorkers, holidayWorkers);
    }

    private static List<String> getWorkers() {
        String weekdayWorkers = Console.readLine();
        List<String> parsedWorkers = parseByComma(weekdayWorkers);
        validateWorkers(parsedWorkers);
        return parsedWorkers;
    }

    private static void validateWorkers(List<String> workers) {
        validateWorkerCounts(workers);
        if (isInvalidNickNameLength(workers)) {
            throw new IllegalArgumentException(INVALID_NICKNAME_LENGTH.getMessage());
        }
        if (isInvalidNickNameCharacter(workers)) {
            throw new IllegalArgumentException(INVALID_NICKNAME_CHARACTER.getMessage());
        }
        if (new HashSet<>(workers).size() != workers.size()) {
            throw new IllegalArgumentException(DUPLICATED_NICKNAME.getMessage());
        }
    }

    private static void validateWorkerCounts(List<String> parsedWeekdayWorkers) {
        int workerSize = parsedWeekdayWorkers.size();
        if (workerSize < MIN_WORKER_SIZE || MAX_WORKER_SIZE < workerSize) {
            throw new IllegalArgumentException(INVALID_WORKER_COUNTS.getMessage());
        }
    }

    private static boolean isInvalidNickNameLength(List<String> parsedWeekdayWorkers) {
        return parsedWeekdayWorkers.stream()
                .anyMatch(nickName -> nickName.length() < MIN_WORKER_NICKNAME_LENGTH
                        || nickName.length() > MAX_WORKER_NICKNAME_LENGTH);
    }

    private static boolean isInvalidNickNameCharacter(List<String> parsedWeekdayWorkers) {
        return parsedWeekdayWorkers.stream()
                .anyMatch(nickName -> nickName.chars()
                        .anyMatch(character -> character < MIN_KOREAN_CHARACTER || MAX_KOREAN_CHARACTER < character));
    }

    private static void validateWeekdayAndHolidayWorkers(List<String> weekdayWorkers, List<String> holidayWorkers) {
        if (!new HashSet<>(weekdayWorkers).containsAll(holidayWorkers)) {
            throw new IllegalArgumentException(WORKER_NOT_MATCHED.getMessage());
        }
    }

    protected enum InputMessage {
        INPUT_MONTH_DAY("비상 근무를 배정할 월과 시작 요일을 입력하세요> "),
        INPUT_WEEKDAY_EMERGENCY_WORKERS("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> "),
        INPUT_WEEKEND_EMERGENCY_WORKERS("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");

        private final String message;

        InputMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
