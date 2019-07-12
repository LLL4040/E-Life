package packageserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
/**
 * @Author wyx
 * @Data 2019.07.12
 */
public class PackageServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PackageServerApplication.class, args);
    }

}
