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

public class InputView {

    public WorkCalendar readWorkCalendar() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        List<String> parseMonthDayOfWeek = parseByComma(Console.readLine());
        if (parseMonthDayOfWeek.size() != 2) {
            throw new IllegalArgumentException(INVALID_MONTH_DAY.getMessage());
        }
        Month month = convertMonth(parseToInt(parseMonthDayOfWeek.get(0)));
        DayOfWeek dayOfWeek = Day.of(parseMonthDayOfWeek.get(1));
        return new WorkCalendar(month, dayOfWeek);
    }

    private List<String> parseByComma(String input) {
        return Arrays.asList(input.split(","));
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
}
