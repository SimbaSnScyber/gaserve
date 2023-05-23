package za.co.gaserve.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

@Configuration
@PropertySource("classpath:dynamo.dev.properties")
public class LiveDaoConfig extends DaoConfig{
 
    @Value("${amazon.aws.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;
 
    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;
 
    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    @Value("${amazon.aws.region}")
    private String region;
    
    
    
    @Autowired
    private AmazonDynamoDB amazonDynamoDB;
    
    
    
    
    public String getAmazonAWSAccessKey() {
		return amazonAWSAccessKey;
	}




	public String getAmazonAWSSecretKey() {
		return amazonAWSSecretKey;
	}




	public AmazonDynamoDB getAmazonDynamoDB() {
		return amazonDynamoDB;
	}




	@Bean
    public AmazonDynamoDB amazonDynamoDB() {
    	AmazonDynamoDBClientBuilder amazonDynamoDB =  AmazonDynamoDBClientBuilder.standard()
                .withCredentials(credentialsProvider());
        
//        if (!StringUtils.isEmpty(region)) {
//            amazonDynamoDB.withRegion(region);
//        }
        
        if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
            amazonDynamoDB.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, region));
        }

        return amazonDynamoDB.build();
    }
    
   
  
}