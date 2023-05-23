//package io.swagger;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.ExitCodeGenerator;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import co.za.gaserve.business.BusinessServiceConfig;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import za.co.gaserve.dao.DaoConfig;
//import za.co.gaserve.dao.LiveDaoConfig;
//import za.co.gaserve.dao.TestDaoConfig;
//import za.co.gaserve.entities.*;
//
//@EnableWebMvc
//@SpringBootApplication
//@ComponentScan(basePackages = {  })
//public class LocalSpringBoot2 implements CommandLineRunner {
//
//    @Override
//    public void run(String... arg0) throws Exception {
//        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
//            throw new ExitException();
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        new SpringApplication(LocalSpringBoot2.class,BusinessServiceConfig.class, TestDaoConfig.class).run(args);
//    }
//
//    class ExitException extends RuntimeException implements ExitCodeGenerator {
//        private static final long serialVersionUID = 1L;
//
//        @Override
//        public int getExitCode() {
//            return 10;
//        }
//
//    }
//}
