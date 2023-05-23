package za.co.gaserve.entities.lists;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BaseDaoTest;


public class PaymentMethodDaoTest extends BaseDaoTest{
 
    @Autowired
    private Dao<PaymentMethod> dao;
    
    public Dao<PaymentMethod> getDao(){return dao;}

    @Test
    public void testDeactivate() {
    	PaymentMethod PaymentMethod = new PaymentMethod();
		
    	String randomUUID = UUID.randomUUID().toString();
    	PaymentMethod.setId(randomUUID);
    	PaymentMethod.setDescription("PAYMENT_METHOD_DESC");
    	
    	dao.create(PaymentMethod);
    	
    	PaymentMethod PaymentMethod2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",PaymentMethod.getId(), PaymentMethod2.getId());

    	PaymentMethod2.deactivate();
    	
    	dao.update(PaymentMethod2);
    	
    	PaymentMethod PaymentMethod3 = dao.findById(randomUUID);
    	
    	Assert.assertFalse(PaymentMethod3.getActive());
    	
    }

    @Test
    public void testUpdate() {
    	PaymentMethod PaymentMethod = new PaymentMethod();
	String randomUUID = UUID.randomUUID().toString();

	PaymentMethod.setId(randomUUID);
	PaymentMethod.setDescription("PAYMENT_METHOD_DESC");
    	
    	dao.create(PaymentMethod);
    	
    	PaymentMethod PaymentMethod2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",PaymentMethod.getId(), PaymentMethod2.getId());

    	PaymentMethod2.setDescription("PAYMENT_METHOD_DESC2");
    	
    	dao.update(PaymentMethod2);
    	
    	PaymentMethod product3 = dao.findById(randomUUID);
    	
    	Assert.assertEquals(product3.getDescription(),"PAYMENT_METHOD_DESC2");	
    }

    @Test
    public void testCreate() {
    	PaymentMethod product = new PaymentMethod();
    	String randomUUID = UUID.randomUUID().toString();
	
    	product.setId(randomUUID);
    	product.setDescription("PAYMENT_METHOD_DESC");
    	
    	dao.create(product);
    	
    	PaymentMethod PaymentMethod2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",product.getId(), PaymentMethod2.getId());
    	Assert.assertEquals("Description mismatch",product.getDescription(), PaymentMethod2.getDescription());
    }
}

