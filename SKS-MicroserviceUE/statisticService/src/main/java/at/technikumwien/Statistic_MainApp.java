package at.technikumwien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = Statistics_CustomJpaRepository.class)
public class Statistic_MainApp {
    public static void main(String[] args) {
        SpringApplication.run(Statistic_MainApp.class, args);
    }
}
