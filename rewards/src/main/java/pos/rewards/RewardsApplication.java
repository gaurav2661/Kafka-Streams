package pos.rewards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class RewardsApplication {
    public static void main(String[] args) {
        SpringApplication.run(RewardsApplication.class);
    }
}
