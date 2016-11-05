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
import restaurant.dao.MenuDao;
import restaurant.model.Menu;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/hibernateContext.xml"})
@Transactional(propagation = Propagation.REQUIRED)
public class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Mock
    private MenuDao menuDao;

    private Menu menu;

    @Before
    public void setUp() throws Exception {
        menuDao = Mockito.mock(MenuDao.class);
        menuService = new MenuService();
        menuService.setMenuDao(menuDao);

        menu = new Menu();
        menu.setId(2);
        menu.setCategory("fruit");
    }

    @Test
    public void doWorkTest() throws Exception {
        menuDao.selectAll();
        verify(menuDao).selectAll();
    }

    @Test
    public void testSetup() throws Exception {
        Assert.assertTrue(menuDao != null);
    }

    @Test
    public void testGetMenu() throws Exception {
        Menu menu2 = new Menu();
        menu2.setId(5);
        menu2.setCategory("water");

        List<Menu> list = new ArrayList<>();
        list.add(menu2);
        list.add(menu);
        when(menuDao.selectAll()).thenReturn(list);

        assertTrue(menuService.getMenu().equals(list));
    }

    @Test
    public void testAddMenu() throws Exception {
        when(menuService.getMenuById((2))).thenReturn(menu);
        menuDao.addInDatabase(menu);
        menuService.addMenu(menu);
        Menu mDoc = menuService.getMenuById(2);
        assertNotNull(mDoc);
        assertTrue(mDoc.getId() == menu.getId());
    }

    @Test
    public void testUpdateMenu() throws Exception {
        Menu menu2 = new Menu();
        menu2.setId(5);
        menu2.setCategory("water");

        when(menuDao.findById(2)).thenReturn(menu);
        Menu find = menuService.getMenuById(2);
        find.setCategory("water");
        menuService.updateMenu(find);

        assertTrue(find.getCategory().equals("water"));
    }

    @Test
    public void testRemoveMenu() throws Exception {
        menuService.addMenu(menu);

        menuService.removeMenu(menuService.getMenuById(2));
        assertNull(menuService.getMenuById(2));
    }

    @Test
    public void testGetMenuById() throws Exception {
        when(menuDao.findById(2)).thenReturn(menu);
        Menu menu1 = menuService.getMenuById(2);
        System.out.println(menu1);
        assertTrue(menu1.equals(menu));
    }
}