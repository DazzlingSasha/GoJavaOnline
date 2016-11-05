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
import restaurant.dao.PreparedDao;
import restaurant.model.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/hibernateContext.xml"})
@Transactional(propagation = Propagation.REQUIRED)
public class PreparedServiceTest {

    @Autowired
    private PreparedService preparedService;

    @Mock
    private PreparedDao preparedDao;

    private PreparedDish prepared;
    private PreparedDish preparedTwo;

    @Before
    public void setUp() throws Exception {
        preparedDao = Mockito.mock(PreparedDao.class);
        preparedService = new PreparedService();
        preparedService.setPreparedDao(preparedDao);

        OrderWaiter orderWaiter =  new OrderWaiter();
        orderWaiter.setId(111);
        orderWaiter.setNumberTable(11);
        Cook cook = new Cook();
        cook.setFirstName("Sasha");

        prepared = new PreparedDish();
        prepared.setId(2);
        prepared.setDatePreparedDish(new Date(1));
        prepared.setIdDish(new Dish());
        prepared.setIdOrder(orderWaiter);
        prepared.setIdUser(new Users());
        prepared.setNameDish("meat by France");
        prepared.setNameUser("Max");
        prepared.setPrepared(cook);

        OrderWaiter orderWaiter2 =  new OrderWaiter();
        orderWaiter2.setId(111);
        orderWaiter2.setNumberTable(22);

        cook.setFirstName("Vika");

        preparedTwo = new PreparedDish();
        preparedTwo.setId(2);
        preparedTwo.setDatePreparedDish(new Date(1));
        preparedTwo.setIdDish(new Dish());
        preparedTwo.setIdOrder(orderWaiter2);
        preparedTwo.setIdUser(new Users());
        preparedTwo.setNameDish("meat by Italy");
        preparedTwo.setNameUser("Ira");
        preparedTwo.setPrepared(cook);
    }

    @Test
    public void doWorkTest() throws Exception {
        preparedDao.selectAll();
        verify(preparedDao).selectAll();
    }

    @Test
    public void testSetup() throws Exception {
        Assert.assertTrue(preparedDao != null);
    }

    @Test
    public void testGetAllPrepared() throws Exception {
        List<PreparedDish> list = new ArrayList<>();
        list.add(prepared);
        list.add(preparedTwo);
        when(preparedDao.selectAllPrepared()).thenReturn(list);

        assertTrue(preparedService.getAllPrepared().equals(list));
    }

    @Test
    public void testGetAllDishes() throws Exception {
        List<PreparedDish> list = new ArrayList<>();
        list.add(prepared);
        list.add(preparedTwo);
        when(preparedDao.selectAll()).thenReturn(list);

        assertTrue(preparedService.getAllDishes().equals(list));
    }

    @Test
    public void testGetSearchTable() throws Exception {
        List<PreparedDish> list = new ArrayList<>();
        list.add(prepared);
        list.add(preparedTwo);

        when(preparedDao.findByNubberTable(11)).thenReturn(list);
        assertTrue(preparedService.getSearchTable(11).equals(list));
    }

    @Test
    public void testGetSearchWaiter() throws Exception {
        List<PreparedDish> list = new ArrayList<>();
        list.add(prepared);
        list.add(preparedTwo);

        Cook cook = new Cook();
        cook.setFirstName("Sasha");

        when(preparedDao.findByName("Sasha")).thenReturn(list);
        assertTrue(preparedService.getSearchWaiter("Sasha").equals(list));
    }

    @Test
    public void testGetSearchDateTakeOrder() throws Exception {
        List<PreparedDish> list = new ArrayList<>();
        list.add(prepared);
        list.add(preparedTwo);

        when(preparedDao.findByDate("01/11/2016")).thenReturn(list);
        assertTrue(preparedService.getSearchDateTakeOrder("01/11/2016").equals(list));
    }
}