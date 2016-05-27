package restaurant.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.jdbc.database.Users;
import restaurant.jdbc.database.UsersDao;

import java.sql.Date;
import java.util.List;

public class UsersController implements MainMethodController<Users> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
    private UsersDao usersDao;
    private PlatformTransactionManager txManager;

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addInDatabase(Users user) {
        LOGGER.info("Add new user! ");
        usersDao.createNewUser(user.getFirstName(), user.getLastName(), user.getBirthday(), user.getPhone(), user.getPositionUser(), user.getSalary());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Users> selectAll() {
        LOGGER.info("Select all users! ");
        return usersDao.allInfoAboutUsers();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteWithDatabase(int id) {
        LOGGER.info("Delete user! ");
        usersDao.deleteUser(id);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateInDatabase(Users user) {
        LOGGER.info("Update user! ");
        System.out.println("usersControllers " + user);
        usersDao.updateUser(user);
    }
}
