package restaurant.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.dao.WarehouseDao;
import restaurant.model.Ingredient;
import restaurant.model.Warehouse;

import java.util.List;

public class WarehouseService {

    private WarehouseDao warehouseDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Warehouse> getWarehouse() {
        return warehouseDao.selectAll();
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Warehouse>  getSearchWarehouse(String name) {
        return warehouseDao.findByName(name);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateQuantity(Warehouse warehouse) {
        warehouseDao.updateInDatabase(warehouse);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public Warehouse findWarehouseById(int id) {
        return warehouseDao.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeWarehouse(Warehouse warehouse) {
        warehouseDao.deleteWithDatabase(warehouse);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void createWarehouse(Ingredient itemIngredient) {
        Warehouse warehouse = new Warehouse();
        warehouse.setIdIngredient(itemIngredient);
        warehouseDao.addInDatabase(warehouse);
    }

    public void setWarehouseDao(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }
}
