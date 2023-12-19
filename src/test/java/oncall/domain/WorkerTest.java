package oncall.domain;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WorkerTest {

    @DisplayName("비상근무 명단을 만든다.")
    @Test
    public void makeEmergencyWork() throws Exception {

        List<String> weekdayWorkers = Arrays.asList("바", "사", "가", "나", "다", "라", "마");
        List<String> holidayWorkers = Arrays.asList("마", "바", "사", "가", "나", "다", "라");
        Worker worker = new Worker(weekdayWorkers, holidayWorkers);
        EmergencyWorkers emergencyWorkers = worker.makeEmergencyWork(LocalDate.of(2023, 4, 1));
        List<EmergencyWorker> workers = emergencyWorkers.getEmergencyWorkers();
        for (EmergencyWorker emergencyWorker : workers) {
            System.out.println(emergencyWorker.getName());
        }
        Assertions.assertThat(workers.get(0).getName().equals("마")).isTrue();
        Assertions.assertThat(workers.get(1).getName().equals("바")).isTrue();
        Assertions.assertThat(workers.get(2).getName().equals("사")).isTrue();
        Assertions.assertThat(workers.get(3).getName().equals("바")).isTrue();
        Assertions.assertThat(workers.get(4).getName().equals("가")).isTrue();
        Assertions.assertThat(workers.get(5).getName().equals("나")).isTrue();
        Assertions.assertThat(workers.get(6).getName().equals("다")).isTrue();
    }
}
