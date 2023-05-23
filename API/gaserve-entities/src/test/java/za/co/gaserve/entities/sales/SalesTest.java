package za.co.gaserve.entities.sales;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BaseDaoTest;
import za.co.gaserve.entities.lists.PaymentMethod;
import za.co.gaserve.entities.user.Customer;






public class SalesTest extends BaseDaoTest {
	
    @Autowired
    private Dao<Sale> dao;
    
    public Dao<Sale> getDao(){return dao;}
    
    List<SaleItem> mylist  =  new ArrayList<SaleItem>();
    
    @Test
    public void testDeactivate() {
    	Sale sale = new Sale();
    	PaymentMethod payment =  new PaymentMethod();
    	Customer customer = new Customer();
    	SaleItem items  =  new SaleItem();
		mylist.add(items);
		
    	String randomUUID = UUID.randomUUID().toString();
    	sale.setId(randomUUID);
    	sale.setCustomer(customer);
    	sale.setPaymentMethod(payment);
    	sale.setTotalExcludingVat(100.00);
    	sale.setTotalIncludingVat(150.00);
    	sale.setVatAmount(15.00);
    	sale.setVatPercentage(0.15);
    	sale.setItems(mylist);
    	
    	dao.create(sale);
    	
    	Sale sale2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("customer mismatch",sale.getCustomer(), sale2.getCustomer());
    	Assert.assertEquals("payment mismatch",sale.getPaymentMethod(), sale2.getPaymentMethod());
    	Assert.assertEquals("total excluding vat mismatch",sale.getTotalExcludingVat(), sale2.getTotalExcludingVat());
    	Assert.assertEquals("tolat including mismatch",sale.getTotalIncludingVat(), sale2.getTotalIncludingVat());
    	Assert.assertEquals("Vat Amount mismatch",sale.getVatAmount(), sale2.getVatAmount());
    	Assert.assertEquals("Vat percentage mismatch",sale.getVatPercentage(), sale2.getVatPercentage());
    	Assert.assertEquals("items mismatch",sale.getItems(), sale2.getItems());
    	sale2.deactivate();
    	
    	dao.update(sale2);
    	
    	Sale sale3 = dao.findById(randomUUID);
    	
    	Assert.assertFalse(sale3.getActive());
    	
    }
    
     
    
    @Test
    public void testUpdate() {
  	Sale sale = new Sale();
  	PaymentMethod payment =  new PaymentMethod();
	Customer customer = new Customer();
	String randomUUID = UUID.randomUUID().toString();

    	sale.setId(randomUUID);
    	sale.setId(randomUUID);
    	sale.setCustomer(customer);
    	sale.setPaymentMethod(payment);
    	sale.setTotalExcludingVat(100.00);
    	sale.setTotalIncludingVat(150.00);
    	sale.setVatAmount(15.00);
    	sale.setVatPercentage(0.15);
    	sale.setItems(mylist);
    	
    	dao.create(sale);
    	
    	Sale sale2 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("Id mismatch",sale.getId(), sale2.getId());

    	Assert.assertEquals("customer mismatch",sale.getCustomer(), sale2.getCustomer());
    	Assert.assertEquals("payment mismatch",sale.getPaymentMethod(), sale2.getPaymentMethod());
    	Assert.assertEquals("total excluding vat mismatch",sale.getTotalExcludingVat(), sale2.getTotalExcludingVat());
    	Assert.assertEquals("tolat including mismatch",sale.getTotalIncludingVat(), sale2.getTotalIncludingVat());
    	Assert.assertEquals("Vat Amount mismatch",sale.getVatAmount(), sale2.getVatAmount());
    	Assert.assertEquals("Vat percentage mismatch",sale.getVatPercentage(), sale2.getVatPercentage());
    	Assert.assertEquals("items mismatch",sale.getItems(), sale2.getItems());
    	
    	dao.update(sale2);
    	
    	Sale sale3 = dao.findById(randomUUID);
    	
    	Assert.assertEquals("customer mismatch",sale.getCustomer(), sale3.getCustomer());
    	Assert.assertEquals("payment mismatch",sale.getPaymentMethod(), sale3.getPaymentMethod());
    	Assert.assertEquals("total excluding vat mismatch",sale.getTotalExcludingVat(), sale3.getTotalExcludingVat());
    	Assert.assertEquals("tolat including mismatch",sale.getTotalIncludingVat(), sale3.getTotalIncludingVat());
    	Assert.assertEquals("Vat Amount mismatch",sale.getVatAmount(), sale3.getVatAmount());
    	Assert.assertEquals("Vat percentage mismatch",sale.getVatPercentage(), sale3.getVatPercentage());
    	Assert.assertEquals("items mismatch",sale.getItems(), sale3.getItems());
    }
    
    
    @Test
    public void testCreate() {
    	Sale sale = new Sale();
    	String randomUUID = UUID.randomUUID().toString();
    	PaymentMethod payment =  new PaymentMethod();
    	Customer customer = new Customer();
    	SaleItem items  =  new SaleItem();
		mylist.add(items);
    	sale.setId(randomUUID);
    	sale.setCustomer(customer);
    	sale.setPaymentMethod(payment);
    	sale.setTotalExcludingVat(100.00);
    	sale.setTotalIncludingVat(150.00);
    	sale.setVatAmount(15.00);
    	sale.setVatPercentage(0.15);
    	sale.setItems(mylist);
    	
    	dao.create(sale);
    	
    	Sale sale2 = dao.findById(randomUUID);
    	Assert.assertEquals("customer mismatch",sale.getCustomer(), sale2.getCustomer());
    	Assert.assertEquals("payment mismatch",sale.getPaymentMethod(), sale2.getPaymentMethod());
    	Assert.assertEquals("total excluding vat mismatch",sale.getTotalExcludingVat(), sale2.getTotalExcludingVat());
    	Assert.assertEquals("tolat including mismatch",sale.getTotalIncludingVat(), sale2.getTotalIncludingVat());
    	Assert.assertEquals("Vat Amount mismatch",sale.getVatAmount(), sale2.getVatAmount());
    	Assert.assertEquals("Vat percentage mismatch",sale.getVatPercentage(), sale2.getVatPercentage());
    	Assert.assertEquals("items mismatch",sale.getItems(), sale2.getItems());
    }
    
  }
