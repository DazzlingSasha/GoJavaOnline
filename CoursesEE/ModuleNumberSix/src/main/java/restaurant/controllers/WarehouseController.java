package restaurant.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.jdbc.database.Warehouse;
import restaurant.jdbc.database.WarehouseDao;

import java.util.List;

public class WarehouseController implements MainMethodControllers<Warehouse>{
    private DataSourceTransactionManager txManager;
    private WarehouseDao warehouseDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseController.class);

    public void setTxManager(DataSourceTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setWarehouseDao(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addInDatabase(Warehouse item) {
        LOGGER.info("Add new items on Warehouse!");
        warehouseDao.createNewWarehouse(item);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Warehouse> selectAll() {
        LOGGER.info("Select all items with Warehouse with JOIN Ingredients!");
        return warehouseDao.warehouseJOINIngredients();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteWithDatabase(int id) {
        LOGGER.info("Delete item with Warehouse, where id: "+ id);
        warehouseDao.deleteItemsWithWarehouse(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateInDatabase(Warehouse item) {
        LOGGER.info("Update item in Warehouse!");
        warehouseDao.updateItemInWarehouse(item);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Warehouse> findByName(String nameItems) {
        LOGGER.info("Find user by name item where name = " + nameItems);
        return warehouseDao.findByNameWarehouse(nameItems);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Warehouse findById(int id) {
        LOGGER.info("Find user by name item where id = " + id);
        return warehouseDao.findByIdWarehouse(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Warehouse> findEndsItemsInWarehouse() {
        LOGGER.info("Find items in warehouse where quantity < 10 with JOIN Ingredients");
        return warehouseDao.endsItemsInWarehouseJOINIngredients();
    }
}
