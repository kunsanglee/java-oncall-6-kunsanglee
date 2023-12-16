package oncall.domain;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.time.LocalDate;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class HolidayTest {

    @DisplayName("법정공휴일인지 반환한다.")
    @ParameterizedTest
    @MethodSource("isHolidayArguments")
    public void isHoliday(LocalDate date) throws Exception {
        Assertions.assertThat(Holiday.isHoliday(date)).isTrue();
    }

    static Stream<Arguments> isHolidayArguments() {
        return Stream.of(
                arguments(LocalDate.of(2023, 1, 1)),
                arguments(LocalDate.of(2023, 3, 1)),
                arguments(LocalDate.of(2023, 5, 5)),
                arguments(LocalDate.of(2023, 6, 6)),
                arguments(LocalDate.of(2023, 8, 15)),
                arguments(LocalDate.of(2023, 10, 3)),
                arguments(LocalDate.of(2023, 10, 9)),
                arguments(LocalDate.of(2023, 12, 25))
        );
    }

    @DisplayName("법정공휴일이 아니면 false를 반환한다..")
    @ParameterizedTest
    @MethodSource("isHolidayFalseArguments")
    public void isHolidayFalse(LocalDate date) throws Exception {
        Assertions.assertThat(Holiday.isHoliday(date)).isFalse();
    }

    static Stream<Arguments> isHolidayFalseArguments() {
        return Stream.of(
                arguments(LocalDate.of(2023, 1, 3)),
                arguments(LocalDate.of(2023, 3, 25)),
                arguments(LocalDate.of(2023, 12, 24))
        );
    }
}
