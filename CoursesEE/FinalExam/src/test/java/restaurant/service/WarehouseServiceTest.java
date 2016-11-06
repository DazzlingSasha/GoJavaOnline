package restaurant.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.dao.WarehouseDao;
import restaurant.model.Ingredient;
import restaurant.model.Warehouse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/hibernateContext.xml"})
@Transactional(propagation = Propagation.REQUIRED)
public class WarehouseServiceTest {

    @Autowired
    private WarehouseService warehouseService;

    @Mock
    private WarehouseDao warehouseDao;

    private Warehouse warehouse;
    private Warehouse warehouse2;

    @Before
    public void setUp() throws Exception {
        warehouseDao = Mockito.mock(WarehouseDao.class);
        warehouseService = new WarehouseService();
        warehouseService.setWarehouseDao(warehouseDao);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(2);
        ingredient.setName("banana");
        ingredient.setUnit("kg");
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(3);
        ingredient2.setName("banana1");
        ingredient2.setUnit("l");

        warehouse = new Warehouse();
        warehouse.setId(2);
        warehouse.setIdIngredient(ingredient);
        warehouse.setQuantity(2.9);

        warehouse2 = new Warehouse();
        warehouse2.setId(4);
        warehouse2.setIdIngredient(ingredient);
        warehouse2.setQuantity(10);
    }

    @Test
    public void doWorkTest() throws Exception {
        warehouseDao.selectAll();
        verify(warehouseDao).selectAll();
    }

    @Test
    public void testSetup() throws Exception {
        Assert.assertTrue(warehouseDao != null);
    }

    @Test
    public void testGetWarehouse() throws Exception {

        List<Warehouse> list = new ArrayList<>();
        list.add(warehouse);
        list.add(warehouse2);
        when(warehouseDao.selectAll()).thenReturn(list);

        assertTrue(warehouseService.getWarehouse().equals(list));
    }

    @Test
    public void testGetSearchWarehouse() throws Exception {

        List<Warehouse> list = new ArrayList<>();
        list.add(warehouse);
        list.add(warehouse2);
        when(warehouseDao.findByName("banana")).thenReturn(list);

        assertTrue(warehouseService.getSearchWarehouse("banana").equals(list));
    }

    @Test
    public void testUpdateQuantity() throws Exception {
        when(warehouseDao.findById(2)).thenReturn(warehouse);
        Warehouse find = warehouseService.findWarehouseById(2);
        find.setQuantity(44);
        warehouseService.updateQuantity(find);

        assertTrue(find.getQuantity() == 44);
    }

    @Test
    public void testFindWarehouseById() throws Exception {
        when(warehouseDao.findById(2)).thenReturn(warehouse);
        Warehouse find = warehouseService.findWarehouseById(2);
        System.out.println(find);
        assertTrue(find.equals(warehouse));
    }

    @Test
    public void testRemoveWarehouse() throws Exception {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(6);
        ingredient.setName("banana3");
        ingredient.setUnit("g");

        warehouseService.createWarehouse(ingredient);

        warehouseService.removeWarehouse(warehouseService.findWarehouseById(2));
        assertNull(warehouseService.findWarehouseById(2));
    }

    @Test
    public void testCreateWarehouse() throws Exception {
        when(warehouseService.findWarehouseById(2)).thenReturn(warehouse);
        warehouseDao.addInDatabase(warehouse);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(6);
        ingredient.setName("banana3");
        ingredient.setUnit("g");

        warehouseService.createWarehouse(ingredient);
        Warehouse mDoc = warehouseService.findWarehouseById(2);
        assertNotNull(mDoc);
        assertTrue(mDoc.getId() == warehouse.getId());
    }
}