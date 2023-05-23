package za.co.gaserve.entities.sales;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BaseDaoTest;
import za.co.gaserve.entities.stock.Product;


public class SaleItemTest extends BaseDaoTest{
 
    @Autowired
    private Dao<SaleItem> dao;
    
    public Dao<SaleItem> getDao(){return dao;}

    @Test
    public void testDeactivate() {
    	SaleItem saleItem = new SaleItem();
    	Product product = new Product();
    	String randomUUID = UUID.randomUUID().toString();
    	int quantity = 200;
    	double totalAmount = 200000.43;
    	
    	saleItem.setId(randomUUID);
    	saleItem.setProduct(product);
    	saleItem.setQuantity(quantity);
    	saleItem.setTotalAmount(totalAmount);
    	
    	dao.create(saleItem);
    	
    	SaleItem saleItem2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",saleItem.getId(), saleItem2.getId());

    	saleItem2.deactivate();
    	
    	dao.update(saleItem2);
    	
    	SaleItem saleItem3 = dao.findById(randomUUID);
    	
    	Assert.assertFalse(saleItem3.getActive());
    }

    @Test
    public void testUpdate() {
    	SaleItem saleItem = new SaleItem();
    	Product product = new Product();
	String randomUUID = UUID.randomUUID().toString();
	int quantity = 100;
	double totalAmount = 45454.34;

	saleItem.setId(randomUUID);
	saleItem.setQuantity(quantity);
	saleItem.setTotalAmount(totalAmount);
    	
    	dao.create(saleItem);
    	
    	SaleItem saleItem2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",saleItem.getId(), saleItem2.getId());

    	saleItem2.setProduct(product);
    	
    	dao.update(saleItem2);
    	
    	SaleItem saleItem3 = dao.findById(randomUUID);
    	
    	Assert.assertEquals(saleItem.getProduct(),null);	
    }

    @Test
    public void testCreate() {
    	SaleItem saleItem = new SaleItem();
    	String randomUUID = UUID.randomUUID().toString();
    	double totalAmount = 2342.32;
    	int quantity = 5;
	
    	saleItem.setId(randomUUID);
    	saleItem.setTotalAmount(totalAmount);
    	saleItem.setId(randomUUID);
    	saleItem.setQuantity(quantity);
    	
    	dao.create(saleItem);
    	
    	SaleItem saleItem2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",saleItem.getId(), saleItem2.getId());
    }
}