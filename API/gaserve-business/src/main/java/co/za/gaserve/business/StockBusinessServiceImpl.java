package co.za.gaserve.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BusinessException;
import za.co.gaserve.entities.corrective.Corrective;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.regions.RetailerFilter;
import za.co.gaserve.entities.stock.*;
import za.co.gaserve.entities.user.User;

import static java.time.LocalDate.now;

public class StockBusinessServiceImpl implements StockBusinessService {

    @Autowired
    private Dao<Stocktaking> stocktakingDao;

    @Autowired
    private Dao<User> userDao;

    @Autowired
    private Dao<Receiving> receivingDao;

    @Autowired
    private Dao<Stock> stockDao;

    @Autowired
    private Dao<Corrective> correctiveDao;


    @Autowired
    private UserBusinessService userBusinessService;

    @Override
    public void balance(String stocktakingId, String userId, String reason) {

        Stocktaking stocktaking = stocktakingDao.findById(stocktakingId);

        if (stocktaking.getBalances()) {
            throw new RuntimeException("Already balances.");
        }

        User user = userDao.findById(userId);
        stocktaking.balanceOut(user, reason);
        stocktakingDao.update(stocktaking);
    }

    @Override
    public List<Stock> findStockByRetailer(Retailer retailer) {
        return RetailerFilter.filterByRetailer(retailer, stockDao.findAll());
    }

    public void receive(Receiving receiving, Retailer retailer) throws BusinessException, NullPointerException {
        Stock stock = findByProducts(retailer, null);
        stock.receive(receiving);
        stockDao.update(stock);
    }

    @Override
    public void receiveItem(ReceivedItem receiving, Retailer retailer) {
        Stock stock = findByProducts(retailer, receiving.getProduct());
        stock.receiveItem(receiving);
        stockDao.update(stock);
    }

    private Stock findByProducts(Retailer retailer, Product product) throws BusinessException, NullPointerException {
        List<Stock> stocks = stockDao.findAll();
        for (Stock stock : stocks) {
            if (retailer.getId().equals(stock.getRetailer().getId())
                    && product.getId().equals(stock.getProduct().getId())) {
                return stock;
            }
        }
        return null;
    }

    @Override
    public Stocktaking takeStock(Stocktaking stocktaking) throws NullPointerException, BusinessException {
        LocalDate date = now();

        if(stocktaking == null) {
            throw new BusinessException("Stocktaking cannot be null", HttpStatus.NOT_FOUND);
        }

        if (stocktaking.getStocktakeDate() == null) {
            throw new BusinessException("The stocktaking date cannot be null", HttpStatus.NOT_ACCEPTABLE);
        } else {
            if (!new Date().toString().substring(0, 10).equals(stocktaking.getStocktakeDate().toString().substring(0, 10))) {
                throw new BusinessException("Stocktaking not captured today.", HttpStatus.NOT_ACCEPTABLE);
            }
        }

        List<Stock> Allstocks = stockDao.findAll();
        List<Stock> stockList = new ArrayList<>();

        User user = stocktaking.getUser();
        Retailer retailer = stocktaking.getRetailer();

        if (!retailer.getManager().getId().equals(user.getId())) {
            throw new BusinessException("User is not the retail manager.", HttpStatus.UNAUTHORIZED);
        }

        for (Stock stock : Allstocks) {
            if (stock.getRetailer().getId().equals(retailer.getId())) {
                stockList.add(stock);
            }
        }

        boolean balances = stocktaking.compare(stockList);
        stocktaking = stocktakingDao.create(stocktaking);

        if (!balances) {
            userBusinessService.lockUserUnbalanced(user.getId());

            List<Stock> expectedStock = stockDao.findByField("retailer", retailer);

            Corrective corrective = new Corrective(user, retailer, "Stocktaking unbalanced", stocktaking.getDifferences());
            corrective.setBeenCorrected(false);
            corrective.setStockTakeId(stocktaking.getId());

            correctiveDao.create(corrective);

        } else {
            userBusinessService.updateUserStatus(user.getId());
        }

        return stocktaking;
    }

    @Override
    public List<Stock> findByDate(Date date) throws BusinessException, NullPointerException {
        return stockDao.findByField("date", date);
    }

    public List<Stock> receiveByDate(Date date) {
        List<Stock> stock = stockDao.findByField("date", date);
        if (!stock.isEmpty()) {
            return stock;
        } else {
            return null;
        }
    }

    @Override
    public Stock findById (String id) throws NullPointerException, BusinessException{
        return stockDao.findById(id);
    }

    @Override
    public List<Stock> findAll () throws NullPointerException, BusinessException {
        return stockDao.findAll();
    }

    @Override
    public List<Stock> findOpeningStockByRetailer(Retailer retailer) {

        Long time = new Date().getTime();
        Date midnight = new Date(time - time % (24 * 60 * 60 * 1000));

        List<Stock> openingStock = new ArrayList<>();

        List<Stocktaking> allTodayStockTake = stocktakingDao.findAfterDate(midnight);

        if (allTodayStockTake==null){
            return null;
        }

        Stocktaking todayStockTake = null;

        for (Stocktaking stocktaking: allTodayStockTake){
            if(stocktaking.getRetailer().getId().equals(retailer.getId())){
                todayStockTake = stocktaking;
                break;
            }
        }

        if (todayStockTake == null){
            return null;
        }

        if (todayStockTake.getBalances().equals(true)){

            for (StocktakingEntry stocktakingEntry: todayStockTake.getStockCountedByRetailer()){
                Stock stock = new Stock();

                Product product = stocktakingEntry.getProduct();
                double quantity = stocktakingEntry.getQuantity();
                stock.setProduct(product);
                stock.setQuantity(quantity);
                openingStock.add(stock);
            }
            return openingStock;
        }

        return null;
    }

    @Override
    public List<Stock> findReceivedStockByRetailer(Retailer retailer) {

        Long time = new Date().getTime();
        Date midnight = new Date(time - time % (24 * 60 * 60 * 1000));

        List<Stock> receivingToday = new ArrayList<>();

        List<Receiving> allTodaysReceivedStock = receivingDao.findAfterDate(midnight);

        if (allTodaysReceivedStock==null){
            return null;
        }

        for (Receiving receiving: allTodaysReceivedStock){
            if(receiving.getRetailer().getId().equals(retailer.getId())){

                for(ReceivedItem receivedItem:receiving.getItems()){

                    Stock stock= new Stock();
                    Product product = receivedItem.getProduct();
                    double quantity = receivedItem.getQuantity();
                    stock.setProduct(product);
                    stock.setQuantity(quantity);


                    if (receivingToday.size()==0) {
                        receivingToday.add(stock);

                    }else{

                        boolean hasBeenReceived = false;
                        int index = 0;

                        for (int i = 0;i<receivingToday.size();i++){
                            if (product.getId().equals(receivingToday.get(i).getProduct().getId())){
                                hasBeenReceived=true;
                                index = i;
                                break;
                            }else{
                                continue;
                            }
                        }

                        if (!hasBeenReceived){
                            receivingToday.add(stock);
                        }else{
                            double quantityAlreadyReceived = receivingToday.get(index).getQuantity();
                            receivingToday.get(index).setQuantity(quantityAlreadyReceived + quantity);
                        }
                    }

                }
            }else{
                continue;
            }
        }

        return receivingToday;
    }
}