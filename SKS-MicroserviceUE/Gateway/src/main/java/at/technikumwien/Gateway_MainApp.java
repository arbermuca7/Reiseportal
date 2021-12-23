package at.technikumwien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// see http://localhost:5555/actuator/gateway/routes

@SpringBootApplication
public class Gateway_MainApp {

    public static void main(String[] args) {
        SpringApplication.run(Gateway_MainApp.class, args);
    }

}
