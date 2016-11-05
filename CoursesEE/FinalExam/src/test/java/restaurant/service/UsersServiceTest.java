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
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.dao.UsersDao;
import restaurant.model.Position;
import restaurant.model.Users;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/hibernateContext.xml"})
@Transactional(propagation = Propagation.REQUIRED)
public class UsersServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private UsersService usersService;

    @Mock
    UsersDao usersDao;

    @Before
    public void init() throws Exception {
        usersDao = Mockito.mock(UsersDao.class);
        usersService = new UsersService();
        usersService.setUsersDao(usersDao);
    }
    @Test
    public void doWorkTest() throws Exception {
        usersDao.selectAll();
        verify(usersDao).selectAll();
    }

    @Test
    public void testSetup() throws Exception {
        Assert.assertTrue(usersDao != null);
    }


    @Test
    public void testGetUsers() throws Exception {
        Users user = new Users();
        user.setId(1);
        user.setFirstName("First");
        user.setLastName("Last");
        user.setPhone("333-33-33");
        user.setBirthday(new Date(1));
        user.setPositionUser(Position.WAITER);

        Users user1 = new Users();
        user1.setId(2);
        user1.setFirstName("First");
        user1.setLastName("Last");
        user1.setPhone("333-33-33");
        user1.setBirthday(new Date(1));
        user1.setPositionUser(Position.WAITER);

        List<Users> list = new ArrayList<>();
        list.add(user);
        list.add(user1);

        Mockito.when(usersDao.selectAll()).thenReturn(list);
//        System.out.println(usersService.getUsers());
        assertEquals(usersService.getUsers(), list);
    }

    @Test
    public void testGetUserById() throws Exception {
        Users user = new Users();
        user.setId(1);
        user.setFirstName("First");
        user.setLastName("Last");
        user.setPhone("333-33-33");
        user.setBirthday(new Date(1));
        user.setPositionUser(Position.WAITER);

        Users user1 = new Users();
        user1.setId(2);
        user1.setFirstName("First");
        user1.setLastName("Last");
        user1.setPhone("333-33-33");
        user1.setBirthday(new Date(1));
        user1.setPositionUser(Position.WAITER);

        when(usersDao.findById(2)).thenReturn(user1);
        Users userIdTwo = usersService.getUserById(2);
        System.out.println(userIdTwo);
        assertTrue(userIdTwo.getId() == 2);
    }

    @Test
    public void testAddUser() throws Exception {
        Users user = new Users();
        user.setId(1);
        user.setFirstName("First");
        user.setLastName("Last");
        user.setPhone("333-33-33");
        user.setBirthday(new Date(1));
        user.setPositionUser(Position.WAITER);
        when(usersService.getUserById(1)).thenReturn(user);
        usersDao.addInDatabase(user);
        usersService.addUser(user);
        Users mDoc = usersService.getUserById(1);
        assertNotNull(mDoc);
        assertTrue(mDoc.getLastName().equals("Last"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        Users user = new Users();
        user.setId(3);
        user.setFirstName("First");
        user.setLastName("Last");
        user.setPhone("333-33-33");
        user.setBirthday(new Date(1));
        user.setPositionUser(Position.WAITER);

        when(usersDao.findById(3)).thenReturn(user);
        Users findUser = usersService.getUserById(3);
        findUser.setLastName("BOB");
        usersService.updateUser(findUser);

        assertTrue(user.getLastName().equals("BOB"));
    }

    @Test
    public void testRemoveUser() throws Exception {
        Users user = new Users();
        user.setId(3);
        user.setFirstName("First");
        user.setLastName("Last");
        user.setPhone("333-33-33");
        user.setBirthday(new Date(1));
        user.setPositionUser(Position.WAITER);

        usersService.addUser(user);

        usersService.removeUser(usersService.getUserById(3));
        assertNull(usersService.getUserById(3));

    }

}