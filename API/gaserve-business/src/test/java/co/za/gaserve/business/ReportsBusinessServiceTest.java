package co.za.gaserve.business;


import com.amazonaws.services.s3.event.S3EventNotification;
import junit.framework.Assert;
import org.joda.time.Days;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import za.co.gaserve.dao.Dao;
import za.co.gaserve.dao.TestDaoConfig;
import za.co.gaserve.entities.BusinessException;
import za.co.gaserve.entities.MyDateUtil;
import za.co.gaserve.entities.lists.UserStatus;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.reports.DailyReport;
import za.co.gaserve.entities.reports.MonthlyReport;
import za.co.gaserve.entities.reports.ReportEntry;
import za.co.gaserve.entities.sales.SalesDao;
import za.co.gaserve.entities.stock.Product;
import za.co.gaserve.entities.stock.StocktakingEntry;
import za.co.gaserve.entities.user.User;
import org.junit.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.LocalDate.now;

@RunWith(SpringJUnit4ClassRunner.class)
@ConfigurationProperties(prefix = "test")
@ContextConfiguration(classes={TestDaoConfig.class,BusinessServiceConfig.class})
public class ReportsBusinessServiceTest {

    @Autowired
    private SalesDao salesDao;

    @Autowired
    private ReportsBusinessService reportsBusinessService;

    @Autowired
    Dao<Product> productDao;

    @Autowired
    Dao<User> userDao;

    @Autowired
    Dao<Retailer> retailerDao;

    @Autowired
    Dao<UserStatus> userStatusDao;

    @Autowired
    Dao<DailyReport> dailyReportDao;

    @Autowired
    Dao<MonthlyReport> monthlyReportDao;

    // Entities
    User user;
    User user2;
    Retailer retailer;
    Retailer retailer2;
    Product product;

    @Before
    public void setup() throws InterruptedException{
        // User
        userStatusDao.create(new UserStatus("ACTIVE", "ACTIVE"));
        userStatusDao.create(new UserStatus("DISABLED","DISABLED"));
        userStatusDao.create(new UserStatus("STOCKTAKE_UNBALANCED","STOCKTAKE_UNBALANCED"));
        userStatusDao.create(new UserStatus("STOCKTAKE_PENDING","STOCKTAKE_PENDING"));
        user = new User("eddy@cq.co.za", userStatusDao.findById("ACTIVE"));
        user2 = new User("Nicky@cq.co.za", userStatusDao.findById("DISABLED"));
        userDao.create(user);
        userDao.create(user2);

        // Product
        product = new Product("Issa Product", "Stove", 340.34);
        productDao.create(product);

        // Retailer
        retailer = new Retailer("Eddy", user);
        retailerDao.create(retailer);
        retailer2 = new Retailer("Nicky", user2);

        retailerDao.create(retailer);
        retailerDao.create(retailer2);

        salesDao.createTable();
        dailyReportDao.createTable();
        retailer2.setActive(false);
        retailerDao.update(retailer2);

    }

    @After
    public void cleanup() {
        userStatusDao.deleteAll();
        userDao.deleteAll();
        productDao.deleteAll();
        //retailerDao.deleteAll();
        //dailyReportDao.deleteAll();
        /*
        monthlyReportDao.deleteAll();
        sdaghdsafhgsadf
        */

    }

    @Test
    public void testCloseAllDaysBeforeToday(){

        LocalDate today = now();
        int dayOfMonth = today.getDayOfMonth();
        int month = today.getMonthValue();
        int year = today.getYear();
        LocalDate startDate = LocalDate.of(2018, 12, 1);
        LocalDate endDate = LocalDate.of(year,month,dayOfMonth); // set it to the current day when testing this

        long days = ChronoUnit.DAYS.between(startDate, endDate);

        reportsBusinessService.closeAllDaysBeforeToday(user, retailer);

        int numOfReports = dailyReportDao.findAll().size();

        org.junit.Assert.assertEquals("Correct reports & previous days",days,numOfReports);
    }

    @Test
    public void testCloseAllDaysBeforeTodayForDifferentRetailers(){

        LocalDate today = now();
        int dayOfMonth = today.getDayOfMonth();
        int month = today.getMonthValue();
        int year = today.getYear();
        LocalDate startDate = LocalDate.of(2018, 12, 1);
        LocalDate endDate = LocalDate.of(year,month,dayOfMonth); // set it to the current day when testing this

        long days = ChronoUnit.DAYS.between(startDate, endDate);

        reportsBusinessService.closeAllDaysBeforeToday(user, retailer);
        reportsBusinessService.closeAllDaysBeforeToday(user, retailer2);

        int numOfRetailers = retailerDao.findAll().size();
        int numOfReports = (dailyReportDao.findAll().size());
        int exp = (int) days * numOfRetailers;
        Assert.assertEquals("Two different retailers",exp,numOfReports);
    }

    @Test
    public void testCloseAllDaysBeforeTodayForNullObjects(){
        user = null;
        retailer = null;
        String handling = "";
        try{
        reportsBusinessService.closeAllDaysBeforeToday(user,retailer);
        }catch (BusinessException be){
            handling = be.getMessage();
        }
        Assert.assertEquals("Null Retailer/Retailer", "Null User/Retailer",handling);
    }

    @Test
    public void testCloseDay(){ retailer = null;
        Date day = MyDateUtil.createDate(2018, 10, 30);
        reportsBusinessService.getDailyReport(retailer,day);
        int numDay = 1;

            List<Product> products = new ArrayList<>();

            Product productGas = productDao.findById("Gas");
            Product productStove = productDao.findById("Stove");
            Product productCylinder = productDao.findById("Cylinder");

            products.add(productStove);
            products.add(productGas);
            products.add(productCylinder);

            reportsBusinessService.closeDay(products, user, retailer, day);

            int numberOfReports = dailyReportDao.findAll().size();

            Assert.assertEquals("Assert one daily report was created", numDay, numberOfReports);

    }

    @Test
    public void testCloseDayWrongDay(){

            List<Product> products = new ArrayList<>();
            Date day = MyDateUtil.createDate(2018, 12, 14);

            Product productGas = productDao.findById("Gas");
            Product productStove = productDao.findById("Stove");
            Product productCylinder = productDao.findById("Cylinder");

            products.add(productStove);
            products.add(productGas);
            products.add(productCylinder);

            String handling = "";

            try{
            reportsBusinessService.closeDay(products, user, retailer, day);
            }catch (BusinessException be){
                handling = be.getMessage();
            }

            Assert.assertEquals("Trying to close for a day after today", "Future Date", handling);
    }

    @Test
    public void testCloseMonth(){
        Date day = MyDateUtil.createDate(2018, 10, 31);

        List<Product> products = new ArrayList<>();

        Product productGas = productDao.findById("Gas");
        Product productStove = productDao.findById("Stove");
        Product productCylinder = productDao.findById("Cylinder");

        products.add(productStove);
        products.add(productGas);
        products.add(productCylinder);

        reportsBusinessService.closeMonth(products,user,retailer,day);

        int numberOfReports = monthlyReportDao.findAll().size();
        Assert.assertEquals("Assert the whole month reports was created", 1, numberOfReports);
    }

    @Test
    public void testGetPastDays(){
        List<Product> products = new ArrayList<>();
        Product productGas = productDao.findById("Gas");
        Product productStove = productDao.findById("Stove");
        Product productCylinder = productDao.findById("Cylinder");

        products.add(productStove);
        products.add(productGas);
        products.add(productCylinder);

        int numberOfDaysFromNow = 0;
        reportsBusinessService.getPastDays(products,user,retailer,numberOfDaysFromNow);
        int numberOfReports = dailyReportDao.findAll().size();

        Assert.assertEquals(" ",0,numberOfReports);
    }

    @Test
    public void testGetPastDaysForWrongDaysFromNow(){
        List<Product> products = new ArrayList<>();
        Product productGas = productDao.findById("Gas");
        Product productStove = productDao.findById("Stove");
        Product productCylinder = productDao.findById("Cylinder");

        products.add(productStove);
        products.add(productGas);
        products.add(productCylinder);

        int numberOfDaysFromNow = 55;
        reportsBusinessService.getPastDays(products,user,retailer,numberOfDaysFromNow);
        int numberOfReports = dailyReportDao.findAll().size();

        Assert.assertEquals("Wrong days Fetched",0,numberOfReports);
    }

    @Test
    public void testGetPastDaysForNullUser(){
        List<Product> products = new ArrayList<>();
        Product productGas = productDao.findById("Gas");
        Product productStove = productDao.findById("Stove");
        Product productCylinder = productDao.findById("Cylinder");

        products.add(productStove);
        products.add(productGas);
        products.add(productCylinder);

        int numberOfDaysFromNow = 0;
        user = null;
        reportsBusinessService.getPastMonths(products,user,retailer,numberOfDaysFromNow);
        int numberOfReports = dailyReportDao.findAll().size();

        Assert.assertEquals("Null User",0,numberOfReports);

    }

    @Test
    public void testGetPastDaysForNullRetailer(){
        List<Product> products = new ArrayList<>();
        Product productGas = productDao.findById("Gas");
        Product productStove = productDao.findById("Stove");
        Product productCylinder = productDao.findById("Cylinder");

        products.add(productStove);
        products.add(productGas);
        products.add(productCylinder);

        int numberOfDaysFromNow = 0;
        retailer = null;
        reportsBusinessService.getPastMonths(products,user,retailer,numberOfDaysFromNow);
        int numberOfReports = dailyReportDao.findAll().size();

        Assert.assertEquals("Null Retailer",0,numberOfReports);

    }

    @Test
    public void testPastMonths(){
        List<Product> products = new ArrayList<>();
        Product productGas = productDao.findById("Gas");
        Product productStove = productDao.findById("Stove");
        Product productCylinder = productDao.findById("Cylinder");

        products.add(productStove);
        products.add(productGas);
        products.add(productCylinder);

        int numberOfMonthsFromNow = 1;

        reportsBusinessService.getPastMonths(products,user,retailer,numberOfMonthsFromNow);
        int numberOfReports = monthlyReportDao.findAll().size();

        Assert.assertEquals("Past Months",1,numberOfReports);
    }

    @Test
    public void testPastMonthsForNullUser(){
        List<Product> products = new ArrayList<>();
        Product productGas = productDao.findById("Gas");
        Product productStove = productDao.findById("Stove");
        Product productCylinder = productDao.findById("Cylinder");

        products.add(productStove);
        products.add(productGas);
        products.add(productCylinder);
        user = null;
        int numberOfMonthsFromNow = 0;

        reportsBusinessService.getPastMonths(products,user,retailer,numberOfMonthsFromNow);
        int numberOfReports = monthlyReportDao.findAll().size();

        Assert.assertEquals("Null User",0,numberOfReports);

    }

    @Test
    public void testPastMonthsForNullRetailer(){
        List<Product> products = new ArrayList<>();
        Product productGas = productDao.findById("Gas");
        Product productStove = productDao.findById("Stove");
        Product productCylinder = productDao.findById("Cylinder");

        products.add(productStove);
        products.add(productGas);
        products.add(productCylinder);
        retailer = null;
        int numberOfMonthsFromNow = 0;

        reportsBusinessService.getPastMonths(products,user,retailer,numberOfMonthsFromNow);
        int numberOfReports = monthlyReportDao.findAll().size();

        Assert.assertEquals("Null Reatiler",0,numberOfReports);

    }

    @Test
    public void testGetDailyReports(){
        Date day = MyDateUtil.createDate(2018, 12, 1);
        int numberOfReports = dailyReportDao.findAll().size();
        reportsBusinessService.getDailyReport(retailer,day);
        Assert.assertEquals("this should pass",1,numberOfReports);
    }

    @Test
    public void testGetDailyReportsForNullRetailer(){
        retailer = null;
        Date day = MyDateUtil.createDate(2018, 10, 1);
        reportsBusinessService.getDailyReport(retailer,day);
        int numOfReport = dailyReportDao.findAll().size();
        Assert.assertEquals("Null Retailer", 0,numOfReport);
    }

    @Test
    public void testGetDailyReportsForInvalidDate(){
        Date day = MyDateUtil.createDate(20158, 15, 51);
        reportsBusinessService.getDailyReport(retailer,day);
        int numOfReport = dailyReportDao.findAll().size();
        Assert.assertEquals("Invalid date", 0,numOfReport);
    }

    @Test
    public void testGetLockedRetailers(){
        List<Product> products = new ArrayList<>();
        Product productGas = productDao.findById("Gas");
        Product productStove = productDao.findById("Stove");
        Product productCylinder = productDao.findById("Cylinder");

        products.add(productStove);
        products.add(productGas);
        products.add(productCylinder);
        int numberOfDaysFromNow = 0;

        int numOfActive = retailerDao.findAllInActive().size();
        int numOfLockedRetailersetailers = retailerDao.findAll().size();
        int sum = numOfLockedRetailersetailers - numOfActive;

        int total = reportsBusinessService.getLockedRetailers().size();

        Assert.assertEquals("Locked retailers",sum,total);

    }

}
