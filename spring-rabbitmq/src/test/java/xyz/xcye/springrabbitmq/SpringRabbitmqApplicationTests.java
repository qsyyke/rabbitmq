package xyz.xcye.springrabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class SpringRabbitmqApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(new Date().toString());
    }

}
