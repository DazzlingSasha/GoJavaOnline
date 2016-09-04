package restaurant.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.dao.OrderDao;
import restaurant.model.OrderWaiter;

import java.util.List;

/**
 * Created by Konfetka on 03.09.2016.
 */
public class OrderService {

    private OrderDao orderDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<OrderWaiter> getAllOrders(){
        return orderDao.selectAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<OrderWaiter> getCloseOrders(){
        return orderDao.selectAllCloseOrder();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<OrderWaiter> getOpenOrders(){
        return orderDao.selectAllOpenOrder();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public OrderWaiter getOrder(int id){
        return orderDao.findById(id);
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
