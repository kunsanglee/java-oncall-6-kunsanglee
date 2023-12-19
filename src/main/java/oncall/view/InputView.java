package oncall.view;


import static oncall.exception.ExceptionMessage.INVALID_MONTH;
import static oncall.exception.ExceptionMessage.INVALID_MONTH_DAY;
import static oncall.exception.ExceptionMessage.NOT_NUMBER;

import camp.nextstep.edu.missionutils.Console;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import oncall.domain.Day;
import oncall.domain.WorkCalendar;
import oncall.domain.Workers;

public class InputView {

    private static final String COMMA_DELIMITER = ",";
    private static final int MONTH_DAY_FORMAT_SIZE = 2;
    private static final int MONTH_INDEX = 0;
    private static final int DAY_INDEX = 1;

    public WorkCalendar readWorkCalendar() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        List<String> parseMonthDayOfWeek = parseByComma(Console.readLine());
        if (parseMonthDayOfWeek.size() != MONTH_DAY_FORMAT_SIZE) {
            throw new IllegalArgumentException(INVALID_MONTH_DAY.getMessage());
        }
        Month month = convertMonth(parseToInt(parseMonthDayOfWeek.get(MONTH_INDEX)));
        DayOfWeek dayOfWeek = Day.of(parseMonthDayOfWeek.get(DAY_INDEX));
        return new WorkCalendar(month, dayOfWeek);
    }

    private List<String> parseByComma(String input) {
        return Arrays.asList(input.split(COMMA_DELIMITER));
    }

    private int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER.getMessage());
        }
    }

    private Month convertMonth(int inputMonth) {
        try {
            return Month.of(inputMonth);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(INVALID_MONTH.getMessage());
        }
    }

    public Workers readWorker() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        List<String> weekdayWorkers = parseByComma(Console.readLine());
        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        List<String> holidayWorkers = parseByComma(Console.readLine());
        return Workers.of(weekdayWorkers, holidayWorkers);
    }
}
