package hi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ztHou
 */
@SpringBootApplication
@EnableEurekaClient
public class ServiceHiApplication {
    public static void main(String[] args){
        SpringApplication.run(ServiceHiApplication.class, args);
    }
}
