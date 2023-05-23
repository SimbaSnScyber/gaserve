package io.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import za.co.gaserve.entities.stock.Product;


@Configuration()
public class DataSourceConfiguration {
    // bean definitions follow

    @Bean
    public Product product(){
        return new Product();
    }

}
