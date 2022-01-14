package xyz.xcye.springrabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class SpringRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRabbitmqApplication.class, args);
    }

}
