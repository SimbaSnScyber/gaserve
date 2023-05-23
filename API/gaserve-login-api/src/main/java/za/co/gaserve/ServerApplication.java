package za.co.gaserve;

import co.za.gaserve.business.BusinessServiceConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import za.co.gaserve.dao.LiveDaoConfig;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("za.co.gaserve.api")
public class ServerApplication {


  public static void main(String[] args) throws Exception {
    new SpringApplication(ServerApplication.class, BusinessServiceConfig.class, LiveDaoConfig.class).run(args);
  }
}