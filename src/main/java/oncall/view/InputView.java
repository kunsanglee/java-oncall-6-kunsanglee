package oncall.view;

import static oncall.exception.ExceptionMessage.DUPLICATED_NICKNAME;
import static oncall.exception.ExceptionMessage.INVALID_DAY_OF_MONTH;
import static oncall.exception.ExceptionMessage.INVALID_MONTH_DAY_INPUT;
import static oncall.exception.ExceptionMessage.INVALID_MONTH_INPUT;
import static oncall.exception.ExceptionMessage.INVALID_NICKNAME_CHARACTER;
import static oncall.exception.ExceptionMessage.INVALID_NICKNAME_LENGTH;
import static oncall.exception.ExceptionMessage.INVALID_WORKER_COUNTS;

import camp.nextstep.edu.missionutils.Console;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import oncall.domain.Day;
import oncall.domain.Worker;

public class InputView {

    public int readEmergencyWorkMonthDay() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        String input = Console.readLine();
        List<String> parsedMonthDay = parseByComma(input);
        validateMonthDayForm(parsedMonthDay);
        int month = parseMonth(parsedMonthDay);
        validateMonth(month);
        Day day = Day.of(parsedMonthDay.get(1));
        validateDay(month, day);
        return month;
    }

    private static List<String> parseByComma(String input) {
        return Arrays.asList(input.split(","));
    }

    private static void validateDay(int month, Day day) {
        LocalDate localDate = LocalDate.of(2023, month, 1);
        validateDayOfMonth(day, localDate);
    }

    private static void validateMonthDayForm(List<String> parsedMonthDay) {
        if (parsedMonthDay.size() != 2) {
            throw new IllegalArgumentException(INVALID_MONTH_DAY_INPUT.getMessage());
        }
    }

    private static int parseMonth(List<String> parsedMonthDay) {
        try {
            return Integer.parseInt(parsedMonthDay.get(0));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_MONTH_INPUT.getMessage());
        }
    }

    private static void validateMonth(int month) {
        if (month < 1 || 12 < month) {
            throw new IllegalArgumentException(INVALID_MONTH_INPUT.getMessage());
        }
    }

    private static void validateDayOfMonth(Day day, LocalDate localDate) {
        if (!day.isSame(localDate.getDayOfWeek())) {
            throw new IllegalArgumentException(INVALID_DAY_OF_MONTH.getMessage());
        }
    }

    public Worker readWorker() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        List<String> weekdayWorkers = getWorkers();
        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
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
        // 5명 이상 35명 이하인지 검증
        validateWorkerCounts(workers);
        // 닉네임이 5글자 넘거나 한글이 아닌 경우
        if (isInvalidNickNameLength(workers)) {
            throw new IllegalArgumentException(INVALID_NICKNAME_LENGTH.getMessage());
        }
        // 닉네임이 한글이 아닌경우
        if (isInvalidNickNameCharacter(workers)) {
            throw new IllegalArgumentException(INVALID_NICKNAME_CHARACTER.getMessage());
        }
        // 중복된 사원이 입력되었는지
        if (new HashSet<>(workers).size() != workers.size()) {
            throw new IllegalArgumentException(DUPLICATED_NICKNAME.getMessage());
        }
    }

    private static void validateWorkerCounts(List<String> parsedWeekdayWorkers) {
        int workerSize = parsedWeekdayWorkers.size();
        if (workerSize < 5 || 35 < workerSize) {
            throw new IllegalArgumentException(INVALID_WORKER_COUNTS.getMessage());
        }
    }

    private static boolean isInvalidNickNameLength(List<String> parsedWeekdayWorkers) {
        return parsedWeekdayWorkers.stream()
                .anyMatch(nickName -> nickName.length() > 5);
    }

    private static boolean isInvalidNickNameCharacter(List<String> parsedWeekdayWorkers) {
        return parsedWeekdayWorkers.stream()
                .anyMatch(nickName -> nickName.chars()
                        .anyMatch(character -> character < '가' || '힣' < character));
    }

    private static void validateWeekdayAndHolidayWorkers(List<String> weekdayWorkers, List<String> holidayWorkers) {
        if (!new HashSet<>(weekdayWorkers).containsAll(holidayWorkers)) {
            throw new IllegalArgumentException("평일 사원과 휴일 사원이 일치하지 않습니다.");
        }
    }
}
