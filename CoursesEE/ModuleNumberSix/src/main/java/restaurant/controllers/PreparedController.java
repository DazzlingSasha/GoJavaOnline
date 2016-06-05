package restaurant.controllers;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import restaurant.jdbc.database.PreparedDishDao;

import java.util.List;

public class PreparedController implements MainMethodControllers<PreparedDishDao>{
    private DataSourceTransactionManager txManager;
    private PreparedDishDao preparedDishDao;

    public void setTxManager(DataSourceTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setPreparedDishDao(PreparedDishDao preparedDishDao) {
        this.preparedDishDao = preparedDishDao;
    }

    @Override
    public void addInDatabase(PreparedDishDao item) {

    }

    @Override
    public List<PreparedDishDao> selectAll() {
        return null;
    }

    @Override
    public void deleteWithDatabase(int id) {

    }

    @Override
    public void updateInDatabase(PreparedDishDao item) {

    }

    @Override
    public PreparedDishDao findById(int id) {
        return null;
    }

    @Override
    public List<PreparedDishDao> findByName(String name) {
        return null;
    }
}
