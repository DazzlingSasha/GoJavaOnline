package restaurant.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.jdbc.database.Users;
import restaurant.jdbc.database.UsersDao;

import java.util.List;

public class UsersController implements MainMethodControllers<Users> {
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
        LOGGER.info("Add new user!");
        usersDao.createNewUser(user.getFirstName(), user.getLastName(), user.getBirthday(), user.getPhone(), user.getPositionUser(), user.getSalary());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Users> selectAll() {
        LOGGER.info("Select all users!");
        return usersDao.allInfoAboutUsers();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteWithDatabase(int id) {
        LOGGER.info("Delete user! With id: " + id);
        usersDao.deleteUser(id);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateInDatabase(Users user) {
        LOGGER.info("Update user! With id: " + user.getId());
        usersDao.updateUser(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Users findById(int id) {
        LOGGER.info("Find user by id where id: " + id);
        return usersDao.findByIdUser(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Users> findByName(String name) {
        LOGGER.info("Find all users by first name where name or part name " + name);
        return usersDao.findByNameUser(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Users> allUsersWaiter() {
        LOGGER.info("Select all users waiter ");
        return usersDao.allUsersWaiter();
    }
}
