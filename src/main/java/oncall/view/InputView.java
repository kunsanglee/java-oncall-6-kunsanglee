package oncall.view;

import static oncall.exception.ExceptionMessage.INVALID_DAY_OF_MONTH;
import static oncall.exception.ExceptionMessage.INVALID_MONTH_DAY_INPUT;
import static oncall.exception.ExceptionMessage.INVALID_MONTH_INPUT;

import camp.nextstep.edu.missionutils.Console;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import oncall.domain.Day;

public class InputView {

    public int readEmergencyWorkMonthDay() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        String input = Console.readLine();
        List<String> parsedMonthDay = Arrays.asList(input.split(","));
        validateMonthDayForm(parsedMonthDay);
        int month = parseMonth(parsedMonthDay);
        validateMonth(month);
        Day day = Day.of(parsedMonthDay.get(1));
        validateDay(month, day);
        return month;
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
}
