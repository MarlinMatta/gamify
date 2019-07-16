package edu.uapa.web.app.gamify;

import edu.uapa.web.app.gamify.utils.bootstrap.BootStrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GamifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamifyApplication.class, args);
    }

    @Bean
    BootStrap initialize() {
        return new BootStrap();
    }
}
