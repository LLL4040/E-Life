package bargain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ztHou
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class BargainServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BargainServerApplication.class, args);
    }
}
