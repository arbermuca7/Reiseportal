package at.technikumwien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CashOut_CustomJpaRepository.class)
public class CashOut_MainApp {

    public static void main(String[] args) {
        SpringApplication.run(CashOut_MainApp.class, args);
    }

}
