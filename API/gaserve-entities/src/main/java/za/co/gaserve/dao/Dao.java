package za.co.gaserve.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper.FailedBatch;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.util.TableUtils.TableNeverTransitionedToStateException;

public abstract interface Dao<T>
{
  public abstract T create(T paramT);
  
  public abstract void delete(T paramT);
  
  public abstract T activate(T paramT);
  
  public abstract T deactivate(T paramT);
  
  public abstract void activate(String paramString);
  
  public abstract void deactivate(String paramString);
  
  public abstract T update(T paramT);
  
  public abstract T refresh(T paramT);
  
  public abstract void deleteById(String paramString);
    
  public abstract T findById(String key);

  public abstract List<T> findAll();

  public abstract List<T> findAll(boolean paramBoolean);
  
  public abstract void delete(List<T> paramList);

  List<T> findByScan(DynamoDBScanExpression query);

  public abstract List<T> findByCollectionField(String paramString, Object... paramVarArgs);
  
  public abstract List<T> findByQuery(DynamoDBQueryExpression<T> paramQuery);
  
  public abstract List<T> findByField(String paramString, Object paramObject);
  
  public abstract Collection<T>  create(Collection<T> paramCollection);
  
  public abstract boolean deleteByField(String paramString, Object paramObject);

List<FailedBatch> deleteAll();

List<T> findAllActive();

List<T> findAllInActive();

void createTable() throws TableNeverTransitionedToStateException, InterruptedException;

T findOneByField(String fieldName, Object value);

List<T> findByDateRange(String fieldName, Date from, Date to);

List<T> findByDateRange(DynamoDBQueryExpression<T> queryExpression, String fieldName, Date from, Date to);

List<T> findAfterDate(Date after);
  
  //public abstract List<T> findByRef(Collection<DBRef<T, String>> paramCollection);
}