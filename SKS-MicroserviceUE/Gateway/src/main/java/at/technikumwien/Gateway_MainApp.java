package at.technikumwien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// see http://localhost:5555/actuator/gateway/routes

@SpringBootApplication
public class Gateway_MainApp {

    public static void main(String[] args) {
        SpringApplication.run(Gateway_MainApp.class, args);
    }

    /*@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/article/**").allowedHeaders("Access-Control-Allow-Origin: *");
            }
        };
    }*/
}
