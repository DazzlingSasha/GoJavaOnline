package restaurant.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Users;
import restaurant.model.Hibernate.UsersDao;

import java.util.List;

public class UsersController implements MainMethodControllers<Users> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
    private UsersDao usersDao;

    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addInDatabase(Users user) {
        LOGGER.info("Add new user!");
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Users> selectAll() {
        LOGGER.info("Select all users!");
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select u from Users u").list();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteWithDatabase(Users user) {
        LOGGER.info("Delete user! With id: " + user.getLastName());
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateInDatabase(Users user) {
        LOGGER.info("Update user! With id: " + user.getId());
        sessionFactory.getCurrentSession().update(user);
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
