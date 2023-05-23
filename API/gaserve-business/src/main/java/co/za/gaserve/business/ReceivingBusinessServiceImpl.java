package co.za.gaserve.business;

import org.springframework.beans.factory.annotation.Autowired;
import za.co.gaserve.dao.Dao;
import za.co.gaserve.entities.stock.Product;
import za.co.gaserve.entities.stock.Receiving;

import java.util.List;

public class ReceivingBusinessServiceImpl implements ReceivingBusinessService {

    @Autowired
    private Dao<Receiving> receivingDao;

    @Autowired
    private Dao<Product> productDao;

    @Override
    public List<Receiving> getByProduct(Product product) {
        List<Receiving> receiving = receivingDao.findByField("product", product);

        if (receiving != null && !receiving.isEmpty()){
            return receiving;
        }
        return null;
    }

    @Override
    public Product getProductByName(String name) {
        Product prodReceived = productDao.findById(name);

        if (prodReceived != null){
            return prodReceived;
        }
        return null;
    }

    @Override
    public void receive(List<Receiving> receiving) {

     receivingDao.create(receiving);
    }
    @Override
    public void receive(Receiving receiving) {
        receivingDao.create(receiving);
    }
}
