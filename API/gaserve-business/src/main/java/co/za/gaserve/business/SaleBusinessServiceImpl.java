package co.za.gaserve.business;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.BusinessException;
import za.co.gaserve.entities.regions.Retailer;
import za.co.gaserve.entities.sales.Sale;
import za.co.gaserve.entities.sales.SaleItem;
import za.co.gaserve.entities.stock.Product;
import za.co.gaserve.entities.stock.Stock;

import java.util.List;

public class SaleBusinessServiceImpl implements SaleBusinessService {
    int sold = 0;
    @Autowired
    private Dao<Sale> saleDao;

    @Autowired
    private Dao<Stock> stockDao;

    public void sell(Sale sale) throws Exception {
        //sale.setTags(sale.getTags());

        if (sale != null) {

            Retailer retailer = sale.getRetailer();

            List<SaleItem> items = sale.getItems();
            List<Stock> stocks = stockDao.findAll();

            if (items != null) {
                for (SaleItem item : items) {
                    Product product = item.getProduct();


                    if (item.getQuantity() > 0) {
                        double amount = item.getQuantity();
                        reduceStock(retailer, product, amount);
                    } else {
                        throw new BusinessException(" Invalid Quantity for :" + product.getId() + ". Quantity must be greater than Zero", HttpStatus.BAD_REQUEST);
                    }

                }
            } else {
                throw new BusinessException("No items were selected ", HttpStatus.PRECONDITION_FAILED);
            }


            saleDao.create(sale);
        } else {
            throw new BusinessException("Sale is null", HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public void reduceStock(Retailer retailer, Product product, Double amount) throws Exception {

        List<Stock> stocks = stockDao.findAll();
        if (stocks != null) {
            for (Stock stock : stocks) {

                if (stock.getProduct().getId().equals(product.getId())){
                    if(stock.getQuantity()>= amount) {
                    if (stock.getProduct().getId().equals(product.getId()) && stock.getRetailer().getId().equals(retailer.getId())) {
                        stock.setQuantity((stock.getQuantity() - amount));
                        stockDao.update(stock);
                    } else {
                        continue;
                    }
                } else {
                    throw new BusinessException("Not enough stock Available for "+stock.getProduct().getId()+
                            ". Available stock : "+(int)(stock.getQuantity())+"", HttpStatus.PRECONDITION_FAILED);

                }}


            }

        } else {
            throw new BusinessException("There's no stock", HttpStatus.PRECONDITION_FAILED);
        }
    }
}
