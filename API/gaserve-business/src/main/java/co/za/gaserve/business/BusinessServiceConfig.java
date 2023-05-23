package co.za.gaserve.business;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessServiceConfig {

    @Bean
    public UserBusinessService userBusinessService(){
        return new UserBusinessServiceImpl();
    }

    @Bean
    public ListBusinessService listBusinessService(){
        return new ListBusinessServiceImpl();
    }

    @Bean
    public ReportsBusinessService reportsBusinessService(){
        return new ReportsBusinessServiceImpl();
    }

    @Bean
    public SaleBusinessService saleBusinessService(){return new SaleBusinessServiceImpl();}

    @Bean
    public StockBusinessService stockBusinessService(){return new StockBusinessServiceImpl();}

    @Bean
    public RetailerBusinessService retailerBusinessService(){return new RetailerBusinessServiceImpl();}

    @Bean
    public ReceivingBusinessService receivingBusinessService(){return new ReceivingBusinessServiceImpl();}

    @Bean
    public AdminBusinessService adminBusinessService(){return new AdminBusinessServiceImpl();}
}
