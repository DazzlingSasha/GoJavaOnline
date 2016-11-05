package restaurant.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.dao.PreparedDao;
import restaurant.model.PreparedDish;

import java.sql.Date;
import java.util.List;
@Service
public class PreparedService {
    private PreparedDao preparedDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PreparedDish> getAllPrepared() {
        return preparedDao.selectAllPrepared();
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public List<PreparedDish> getAllDishes() {
        return preparedDao.selectAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PreparedDish> getSearchTable(int numberTable) {
        return preparedDao.findByNubberTable(numberTable);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public List<PreparedDish> getSearchWaiter(String nameWaiter) {
        return preparedDao.findByName(nameWaiter);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PreparedDish> getSearchDateTakeOrder(String date) {
        return preparedDao.findByDate(date);
    }

    public void setPreparedDao(PreparedDao preparedDao) {
        this.preparedDao = preparedDao;
    }
}
