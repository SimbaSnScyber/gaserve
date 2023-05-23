package za.co.gaserve.entities;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import za.co.gaserve.dao.Dao;
import za.co.gaserve.dao.LiveDaoConfig;
import za.co.gaserve.entities.corrective.Corrective;
import za.co.gaserve.entities.lists.PaymentMethod;
import za.co.gaserve.entities.lists.UserStatus;
import za.co.gaserve.entities.regions.Region;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.reports.DailyReport;
import za.co.gaserve.entities.reports.MonthlyReport;
import za.co.gaserve.entities.reports.ReportEntry;
import za.co.gaserve.entities.sales.Sale;
import za.co.gaserve.entities.sales.SaleItem;
import za.co.gaserve.entities.stock.*;
import za.co.gaserve.entities.user.Customer;
import za.co.gaserve.entities.user.User;

import java.time.LocalDate;
import java.util.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LiveDaoConfig.class)
@PropertySource("classpath:dynamo.properties")
public class Bootstrapper {


    @Autowired
    private Dao<User> userDao;

    @Autowired
    private Dao<UserStatus> userStatusDao;

    @Autowired
    private Dao<PaymentMethod> paymentMethodDao;

    @Autowired
    private Dao<za.co.gaserve.entities.stock.Product> productDao;

    @Autowired
    private Dao<Region> regionDao;

    @Autowired
    private Dao<Retailer> retailerDao;

    @Autowired
    private Dao<Sale> saleDao;

    @Autowired
    private Dao<SaleItem> saleItemDao;

    @Autowired
    private Dao<Stocktaking> stocktakingDao;

    @Autowired
    private Dao<DailyReport> dailyReportDao;

    @Autowired
    private Dao<MonthlyReport> monthlyReportDao;

    @Autowired
    private Dao<ReportEntry> reportEntryDao;

    @Autowired
    private Dao<Customer> customerDao;

    @Autowired
    private Dao<Stock> stockDao;

    @Autowired
    private Dao<Receiving> receivingDao;

    @Autowired
    private Dao<Corrective> correctiveDao;

    @Before
    public void setup() throws Exception {

        prepare(userStatusDao);
        prepare(paymentMethodDao);
        prepare(productDao);
        prepare(regionDao);
        prepare(retailerDao);
        prepare(stockDao);


    }

    public static void prepare(Dao dao) throws Exception {
        dao.createTable();
        dao.deleteAll();

    }

    @Test
    public void bootstrapAll() {
        bootstrapUserStatus(userStatusDao);
        bootstrapProducts(productDao);
        bootstrapRegions(regionDao);
        bootstrapRetailers(retailerDao, userDao);
        bootstrapPaymentMethod(paymentMethodDao);
        bootstrapStock(stockDao, productDao, retailerDao);
    }

    public static void bootstrapUserStatus(Dao<UserStatus> userStatusDao) {
        userStatusDao.create(new UserStatus("ACTIVE", "ACTIVE"));
        userStatusDao.create(new UserStatus("STOCKTAKE_PENDING", "STOCKTAKE_PENDING"));
        userStatusDao.create(new UserStatus("DISABLED", "DISABLED"));
        userStatusDao.create(new UserStatus("REGISTERED", "REGISTERED"));
        userStatusDao.create(new UserStatus("STOCKTAKE_UNBALANCED", "STOCKTAKE_UNBALANCED"));
    }

    public void bootstrapPaymentMethod(Dao<PaymentMethod> paymentMethodDao) {
        paymentMethodDao.create(new PaymentMethod("CASH", "CASH"));
        paymentMethodDao.create(new PaymentMethod("ECHO_CASH", "ECHO_CASH"));
    }

    public void bootstrapProducts(Dao<Product> productDao) {
        productDao.create(new Product("LP_Gas", "Gas", 10));
        productDao.create(new Product("9kg_Cylinder", "9kg Cylinder", 20));
        productDao.create(new Product("14kg_Cylinder", "14kg Cylinder", 26));
        productDao.create(new Product("19kg_Cylinder", "9kg Cylinder", 50));
        productDao.create(new Product("48kg_Cylinder", "48kg Cylinder", 90));
        productDao.create(new Product("1P_Stove", "1 Plate Stove", 30));
        productDao.create(new Product("2P_Stove", "2 Plate Stove", 50));
    }

    public void bootstrapRegions(Dao<Region> regionDao) {
        regionDao.create(new Region("NORTHERN", "NORTHERN"));
        regionDao.create(new Region("SOUTHERN", "SOUTHERN"));
    }

    public void bootstrapRetailers(Dao<Retailer> retailerDao, Dao<User> userDao) {
        retailerDao.create(new Retailer("DZ GLAUDINA", userDao.findByField("email", "gaservedev@gmail.com").get(0)));
        retailerDao.create(new Retailer("Whitehouse", userDao.findByField("email", "kpiroddi@gmail.com").get(0)));
        retailerDao.create(new Retailer("Pamuzinda", userDao.findByField("email", "lotharsessentials@gmail.com").get(0)));
        retailerDao.create(new Retailer("Solomiyo", userDao.findByField("email", "elitewildfire@gmail.com").get(0)));
        retailerDao.create(new Retailer("Timire Tencraft", userDao.findByField("email", "glen.ngwako@gmail.com").get(0)));

        List<Retailer> retailers = retailerDao.findAll();

        Region region = regionDao.findById("NORTHERN");
        region.setRetailers(retailers);
        regionDao.update(region);
    }

    public static void bootstrapStock(Dao<Stock> stockDao, Dao<Product> productDao, Dao<Retailer> retailerDao) {

        Product Gas = productDao.findById("LP_Gas");
        Product Stove1P = productDao.findById("1P_Stove");
        Product Stove2P = productDao.findById("2P_Stove");
        Product kg9_Cylinder = productDao.findById("9kg_Cylinder");
        Product kg14_Cylinder = productDao.findById("14kg_Cylinder");
        Product kg19_Cylinder = productDao.findById("19kg_Cylinder");
        Product kg48_Cylinder = productDao.findById("48kg_Cylinder");

        Retailer retailer1 = retailerDao.findOneByField("retailerName", "DZ GLAUDINA");
        Retailer retailer2 = retailerDao.findOneByField("retailerName", "Whitehouse");
        Retailer retailer3 = retailerDao.findOneByField("retailerName", "Pamuzinda");
        Retailer retailer4 = retailerDao.findOneByField("retailerName", "Solomiyo");
        Retailer retailer5 = retailerDao.findOneByField("retailerName", "Timire Tencraft");

        stockDao.create(new Stock(Gas, 1000, retailer2));
        stockDao.create(new Stock(Stove1P, 1000, retailer2));
        stockDao.create(new Stock(Stove2P, 1000, retailer2));
        stockDao.create(new Stock(kg9_Cylinder, 1000, retailer2));
        stockDao.create(new Stock(kg14_Cylinder, 1000, retailer2));
        stockDao.create(new Stock(kg19_Cylinder, 1000, retailer2));
        stockDao.create(new Stock(kg48_Cylinder, 1000, retailer2));

        stockDao.create(new Stock(Gas, 1000, retailer1));
        stockDao.create(new Stock(Stove1P, 1000, retailer1));
        stockDao.create(new Stock(Stove2P, 1000, retailer1));
        stockDao.create(new Stock(kg9_Cylinder, 1000, retailer1));
        stockDao.create(new Stock(kg14_Cylinder, 1000, retailer1));
        stockDao.create(new Stock(kg19_Cylinder, 1000, retailer1));
        stockDao.create(new Stock(kg48_Cylinder, 1000, retailer1));

        stockDao.create(new Stock(Gas, 1000, retailer3));
        stockDao.create(new Stock(Stove1P, 1000, retailer3));
        stockDao.create(new Stock(Stove2P, 1000, retailer3));
        stockDao.create(new Stock(kg9_Cylinder, 1000, retailer3));
        stockDao.create(new Stock(kg14_Cylinder, 1000, retailer3));
        stockDao.create(new Stock(kg19_Cylinder, 1000, retailer3));
        stockDao.create(new Stock(kg48_Cylinder, 1000, retailer3));

        stockDao.create(new Stock(Gas, 1000, retailer4));
        stockDao.create(new Stock(Stove1P, 1000, retailer4));
        stockDao.create(new Stock(Stove2P, 1000, retailer4));
        stockDao.create(new Stock(kg9_Cylinder, 1000, retailer4));
        stockDao.create(new Stock(kg14_Cylinder, 1000, retailer4));
        stockDao.create(new Stock(kg19_Cylinder, 1000, retailer4));
        stockDao.create(new Stock(kg48_Cylinder, 1000, retailer4));

        stockDao.create(new Stock(Gas, 1000, retailer5));
        stockDao.create(new Stock(Stove1P, 1000, retailer5));
        stockDao.create(new Stock(Stove2P, 1000, retailer5));
        stockDao.create(new Stock(kg9_Cylinder, 1000, retailer5));
        stockDao.create(new Stock(kg14_Cylinder, 1000, retailer5));
        stockDao.create(new Stock(kg19_Cylinder, 1000, retailer5));
        stockDao.create(new Stock(kg48_Cylinder, 1000, retailer5));

    }
}
