package oncall.domain;

import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EmergencyWorkerTest {

    @DisplayName("이름이 동일한지 반환한다.")
    @Test
    public void isSame() throws Exception {
        EmergencyWorker worker = new EmergencyWorker("테스트", LocalDate.of(2023, 12, 16));
        Assertions.assertThat(worker.isSame("테스트")).isTrue();
    }
}
