package io.swagger;

import co.za.gaserve.business.BusinessServiceConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import za.co.gaserve.dao.DaoConfig;
import za.co.gaserve.dao.LiveDaoConfig;
import za.co.gaserve.dao.TestDaoConfig;

@SpringBootApplication
@EnableSwagger2

public class Swagger2SpringBoot implements CommandLineRunner {

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Swagger2SpringBoot.class, BusinessServiceConfig.class, LiveDaoConfig.class).run(args);
    }


    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}
