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

public class UsersController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
    private UsersDao usersDao;
    private PlatformTransactionManager txManager;

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public List<Users> getAllUsers() {
        LOGGER.info("Select all users! ");
        return usersDao.allInfoAboutUsers();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Users> getAllUsersAndCreateUser() {
        LOGGER.info("Select all users! ");
        usersDao.createNewUser("Ira", "Vaskina", new Date(1980-01-01), "044-553-22-22", "WAITER", 30000);
        Users users = usersDao.findByIdUser(7);
        if(users.equals(null)){
            LOGGER.error("Can not user with id: "+7);
            throw new RuntimeException();
        }
        System.out.println(users);
        usersDao.deleteUser(7);
        return usersDao.allInfoAboutUsers();
    }
}
