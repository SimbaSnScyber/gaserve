package za.co.gaserve.dao;

import org.springframework.context.annotation.Bean;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import za.co.gaserve.entities.corrective.Corrective;
import za.co.gaserve.entities.lists.PaymentMethod;
import za.co.gaserve.entities.lists.UserStatus;
import za.co.gaserve.entities.regions.Region;
import za.co.gaserve.entities.regions.RetailerDao;
import za.co.gaserve.entities.regions.RetailerDaoImpl;
import za.co.gaserve.entities.reports.DailyReport;
import za.co.gaserve.entities.reports.MonthlyReport;
import za.co.gaserve.entities.reports.ReportEntry;
import za.co.gaserve.entities.sales.Sale;
import za.co.gaserve.entities.sales.SaleItem;
import za.co.gaserve.entities.sales.SalesDao;
import za.co.gaserve.entities.sales.SalesDaoImpl;
import za.co.gaserve.entities.stock.Product;
import za.co.gaserve.entities.stock.Receiving;
import za.co.gaserve.entities.stock.Stock;
import za.co.gaserve.entities.stock.Stocktaking;
import za.co.gaserve.entities.user.Customer;
import za.co.gaserve.entities.user.Group;
import za.co.gaserve.entities.user.Login;
import za.co.gaserve.entities.user.RetailerRole;
import za.co.gaserve.entities.user.Role;
import za.co.gaserve.entities.user.User;

public abstract class DaoConfig {
 
   
    abstract public String getAmazonAWSAccessKey();

    abstract public String getAmazonAWSSecretKey();


	abstract public AmazonDynamoDB getAmazonDynamoDB();

	@Bean
    public Dao<Role> roleDao()
    {
    	return new DaoImpl<Role>(Role.class, getAmazonDynamoDB());
    }

    @Bean
    public Dao<Customer> customerDao()
    {
    	return new DaoImpl<Customer>(Customer.class,getAmazonDynamoDB());
    }

    @Bean
    public Dao<Region> regionDao()
    {
        return new DaoImpl<Region>(Region.class,getAmazonDynamoDB());
    }

    @Bean
    public Dao<Group> groupDao()
    {
    	return new DaoImpl<Group>(Group.class,getAmazonDynamoDB());
    }
    

    @Bean
    public Dao<Login> loginDao()
    {
    	return new DaoImpl<Login>(Login.class,getAmazonDynamoDB());
    }
    
    @Bean
    public Dao<ReportEntry> reportEntryDao()
    {
    	return new DaoImpl<ReportEntry>(ReportEntry.class,getAmazonDynamoDB());
    }

    @Bean
    public Dao<Product> productDao()
    {
    	return new DaoImpl<Product>(Product.class,getAmazonDynamoDB());
    }
    

    @Bean
    public Dao<Receiving> stockReceivingDao()
    {
    	return new DaoImpl<Receiving>(Receiving.class,getAmazonDynamoDB());
    }
    
    @Bean
    public Dao<PaymentMethod> paymentMethodDao()
    {
    	return new DaoImpl<PaymentMethod>(PaymentMethod.class,getAmazonDynamoDB());
    }
    
    @Bean
    public Dao<Stocktaking> stocktakingDao()
    {
    	return new DaoImpl<Stocktaking>(Stocktaking.class,getAmazonDynamoDB());
    }
    
    @Bean
    public Dao<Sale> saleDao()
    {
    	return new DaoImpl<Sale>(Sale.class,getAmazonDynamoDB());
    }

    @Bean
    public Dao<DailyReport> dailyReportDao()
    {
    	return new DaoImpl<DailyReport>(DailyReport.class,getAmazonDynamoDB());
    }
        
    @Bean
    public Dao<UserStatus> userStatusDao()
    {
    	return new DaoImpl<UserStatus>(UserStatus.class,getAmazonDynamoDB());
    }
    
    @Bean
    public Dao<SaleItem> saleItemDao()
    {
    	return new DaoImpl<SaleItem>(SaleItem.class,getAmazonDynamoDB());
    }

    @Bean
    public Dao<Stock> stockDao()
    {
    	return new DaoImpl<Stock>(Stock.class,getAmazonDynamoDB());
    }

    @Bean
    public Dao<User> userDao()
    {
        return new DaoImpl<User>(User.class,getAmazonDynamoDB());
    }

    @Bean
    public Dao<RetailerRole> retailerRoleDao()
    {
        return new DaoImpl<RetailerRole>(RetailerRole.class,getAmazonDynamoDB());
    }

    @Bean
    public Dao<Corrective> correctiveDao() {

	    return new DaoImpl<Corrective>(Corrective.class,getAmazonDynamoDB());
    }

    @Bean
    public Dao<MonthlyReport> mothlyReportDao()
    {
        return new DaoImpl<MonthlyReport>(MonthlyReport.class,getAmazonDynamoDB());
    }

    @Bean
    public SalesDao salesDao()
    {
        return new SalesDaoImpl(getAmazonDynamoDB());
    }

    @Bean
    public RetailerDao customRetailerDao()
    {
        return new RetailerDaoImpl(getAmazonDynamoDB());
    }
   
    @Bean
    public DynamoDBMapper dynamoDBMapper()
    {
    	return new DynamoDBMapper(getAmazonDynamoDB());
    }

    
    @Bean 
    public AWSStaticCredentialsProvider credentialsProvider() 
    {
    	AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(new BasicAWSCredentials(
    	          getAmazonAWSAccessKey(), getAmazonAWSSecretKey()));
    	
    	return credentialsProvider;
    }
    
  
}