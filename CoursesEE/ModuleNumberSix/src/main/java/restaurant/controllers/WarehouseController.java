package restaurant.controllers;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import restaurant.jdbc.database.WarehouseDao;

import java.util.List;

public class WarehouseController implements MainMethodController{
    private DataSourceTransactionManager txManager;
    private WarehouseDao warehouseDao;

    public void setTxManager(DataSourceTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setWarehouseDao(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }


    @Override
    public void addInDatabase(Object item) {

    }

    @Override
    public List selectAll() {
        return null;
    }

    @Override
    public void deleteWithDatabase(int id) {

    }

    @Override
    public void updateInDatabase(Object item) {

    }
}
