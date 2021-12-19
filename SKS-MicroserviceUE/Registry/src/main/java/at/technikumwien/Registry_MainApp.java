package at.technikumwien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Registry_MainApp {

    public static void main(String[] args) {
        SpringApplication.run(Registry_MainApp.class, args);
    }

}
