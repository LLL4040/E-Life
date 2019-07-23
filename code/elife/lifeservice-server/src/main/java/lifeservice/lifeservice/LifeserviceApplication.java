package lifeservice.lifeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication

@EnableEurekaClient
@RestController
/**
 * @author 符永锐
 * @date 2019/07/09*/
public class LifeserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifeserviceApplication.class, args);
    }

}
