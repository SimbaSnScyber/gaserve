 package za.co.gaserve.dao;
import java.text.SimpleDateFormat;
import java.util.*;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper.FailedBatch;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.amazonaws.services.dynamodbv2.util.TableUtils.TableNeverTransitionedToStateException;

import za.co.gaserve.entities.Entity;

 
  public class DaoImpl<T extends Entity> implements Dao<T>
 {
	 
   private AmazonDynamoDB db;
   private DynamoDBMapper mapper;
   private Class<T> clazz;
   
   public DaoImpl(Class<T> clazz, AmazonDynamoDB db)
   {
	 this.clazz = clazz;
     this.db = db;
     mapper = new DynamoDBMapper(db);
   }
   
   public T create(T entity)
   {
     entity.activate();
     entity.prePersist();
     this.mapper.save(entity);
     return entity;
   }
   
 
 
 
   public Collection<T> create(Collection<T> entities)
   {
     List<DynamoDBMapper.FailedBatch> failed = this.mapper.batchSave(entities);
     if(!failed.isEmpty()){
    	 throw new RuntimeException("Batch save failure");
     }
     return entities;
   }
   
   
   public void delete(T entity)
   {
     this.mapper.delete(entity);
   }
   
   public void delete(List<T> entities)
   {
     if ((entities != null) && (!entities.isEmpty())) {
       for (T entity : entities) {
         delete(entity);
       }
     }
   }
   
 
 
 
   public T update(T entity)
   {
    entity.prePersist();
     this.mapper.save(entity);
     return entity;
   }
   
 
 
 
   public T refresh(T entity)
   {
     return findById(entity.getId());
   }
   
 
 
 
   public void deleteById(String key)
   {
     this.mapper.delete(mapper.load(key));
   }
   
 
   public boolean deleteByField(String fieldName, Object value)
   {
     
     List<T> found = findByField(fieldName, value);
     
     if (found.size() > 0) {
       for (T find : found) {
         delete(find);
       }
       return true;
     }
     return false;
   }
   
 
 
 
   public T findById(String key)
   {
/* 122 */     T found = this.mapper.load(clazz,key);
     
/* 124 */     return found;
   }
   
 
//   public T findById(Object value)
//   {
//	   
//	   Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
//       eav.put(":id", new AttributeValue().withS(value.toString()));
//
//       DynamoDBQueryExpression<T> queryExpression = new DynamoDBQueryExpression<T>()
//           .withKeyConditionExpression("id = :id").withExpressionAttributeValues(eav);
//
//       List<T> found = mapper.query(clazz, queryExpression);
//     
//     if (found.size() > 0) {
//       return found.iterator().next();
//     }
//     return null;
//   }

   @Override
   public T findOneByField(String fieldName, Object value)
   {
	   List<T> found = findByField( fieldName,  value);
	   
	   if(found != null && !found.isEmpty()){
		   return found.get(0);
	   }
	   
	   return null;
   }

   
   @Override
   public List<T> findByField(String fieldName, Object value)
   {
	   Map<String,AttributeValue> eav = new HashMap<String,AttributeValue>();
	   if(value instanceof Entity){
       	Entity entity = (Entity)value;
       	eav.put(":"+fieldName, new AttributeValue().withS(value.toString()));//TODO: Test this for related Entities		   
	   }
	   else{
	       	eav.put(":"+fieldName, new AttributeValue().withS(value.toString()));//TODO: Test this for related Entities
	   }
       DynamoDBScanExpression queryExpression = new DynamoDBScanExpression()
           .withFilterExpression(fieldName+" = :"+fieldName).withExpressionAttributeValues(eav);

       return mapper.scan(clazz, queryExpression);
     
   }
   

   @Override
   public List<T> findByDateRange(DynamoDBQueryExpression<T> queryExpression,String fieldName, Date from,Date to)
   {
       SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
       dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
       String fromStr = dateFormatter.format(from);
       String toStr = dateFormatter.format(to);

       Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
       eav.put(":from", new AttributeValue().withS(fromStr));
       eav.put(":to", new AttributeValue().withS(toStr));

       queryExpression.withKeyConditionExpression(fieldName+" > :from and "+fieldName+" < :to").withExpressionAttributeValues(eav);

       return mapper.query(clazz, queryExpression);       
	   
   }

   @Override
   public List<T> findByDateRange(String fieldName, Date from,Date to)
   {
	        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	        String fromStr = dateFormatter.format(from);
	        String toStr = dateFormatter.format(to);

	        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
	        eav.put(":from", new AttributeValue().withS(fromStr));
	        eav.put(":to", new AttributeValue().withS(toStr));

	        DynamoDBQueryExpression<T> queryExpression = new DynamoDBQueryExpression<T>()
	            .withKeyConditionExpression(fieldName+" > :from and "+fieldName+" < :to").withExpressionAttributeValues(eav);

	        return mapper.query(clazz, queryExpression);       
   }

   @Override
   public List<T> findAfterDate(Date after){


     SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
     dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));

     AttributeValue mapped = new AttributeValue();
     mapped = mapped.withS( dateFormatter.format(after));

     Map<String, AttributeValue> args = Collections.singletonMap(":v1", mapped);

     DynamoDBScanExpression query = new DynamoDBScanExpression()
             .withFilterExpression("createdDate  > :v1")
             .withExpressionAttributeValues(args);

     return mapper.scan(clazz,query);

   }
 
   public List<T> findByQuery(DynamoDBQueryExpression<T> query)
   {
		return mapper.query(clazz, query);
   }

   @Override
   public List<T> findByScan(DynamoDBScanExpression query)
   {
     return mapper.scan(clazz, query);
   }

//   public List<T> findByRef(Collection<DBRef<T, String>> refs)
//   {
//     return this.collection.fetch(refs);
//   }
//   
 
   public List<T> findByCollectionField(String fieldName, Object... values)
   {
//     DBQuery.Query query = DBQuery.all(fieldName, values) ;
//     DBCursor<T> found = this.collection.find(query);     
//     return readCursor(found);
	   return null;
   }
   
   @Override
   public List<T> findAll()
   {
       DynamoDBScanExpression queryExpression = new DynamoDBScanExpression();
       return mapper.scan(clazz, queryExpression);
   }

   @Override
   public List<FailedBatch> deleteAll()
   {
       return mapper.batchDelete(findAll());
   }

   @Override
   public List<T> findAllActive()
   {
     return findByField("active", Boolean.valueOf(true));
   }

   @Override
   public List<T> findAllInActive()
   {
     return findByField("active",  Boolean.valueOf(false));
   }
   
   @Override
   public List<T> findAll(boolean active)
   {
     return findByField("active", Boolean.valueOf(active));
   }
   
 
   public T activate(T entity)
   {
     entity.activate();
     return update(entity);
   }
   
 
   public T deactivate(T entity)
   {
     entity.deactivate();
     return update(entity);
   }
   
 
   public void activate(String key)
   {
     T found = findById(key);
     
     if (found == null) {
       throw new NullPointerException("Object not found key:" + key);
     }
     
     activate(found);
   }
   
 
   public void deactivate(String key)
   {
     T found = findById(key);
     
     if (found == null) {
       throw new NullPointerException("Object not found key:" + key);
     }
     
     deactivate(found);
   }
   
   @Override
   public void createTable() throws TableNeverTransitionedToStateException, InterruptedException
   {
     
   	DynamoDBTable table = clazz.getAnnotation(DynamoDBTable.class);

       CreateTableRequest createTableRequest = new CreateTableRequest()
       	.withTableName(table.tableName())
       	.withKeySchema(new KeySchemaElement().withAttributeName("id").withKeyType(KeyType.HASH))
           .withAttributeDefinitions(new AttributeDefinition().withAttributeName("id").withAttributeType(ScalarAttributeType.S))
           .withProvisionedThroughput(new ProvisionedThroughput().withReadCapacityUnits(1L).withWriteCapacityUnits(1L));

       //Create table if it does not exist yet
       TableUtils.createTableIfNotExists(db,createTableRequest);
       // wait for the table to move into ACTIVE state
       TableUtils.waitUntilActive(db, table.tableName());
   }
 }