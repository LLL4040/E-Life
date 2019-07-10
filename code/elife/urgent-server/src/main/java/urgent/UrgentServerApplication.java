package urgent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController

/**
 * UrgentServerApplication class
 *
 * @author wyx
 * @date 2019.07.04
 */
public class UrgentServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UrgentServerApplication.class, args);
    }

}
