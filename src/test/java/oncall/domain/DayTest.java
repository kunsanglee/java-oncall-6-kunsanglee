package oncall.domain;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DayTest {

    @DisplayName("요일 이름으로 Day를 반환받는다.")
    @ParameterizedTest
    @MethodSource("ofArguments")
    public void of(String dayOfWeek, Day expectedDay) throws Exception {
        Assertions.assertThat(Day.of(dayOfWeek)).isEqualTo(expectedDay);
    }

    public static Stream<Arguments> ofArguments() {
        return Stream.of(
                arguments("월", Day.MONDAY),
                arguments("화", Day.TUESDAY),
                arguments("수", Day.WEDNESDAY),
                arguments("목", Day.THURSDAY),
                arguments("금", Day.FRIDAY),
                arguments("토", Day.SATURDAY),
                arguments("일", Day.SUNDAY)
        );
    }
}
