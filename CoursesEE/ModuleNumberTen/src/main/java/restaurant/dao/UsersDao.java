package restaurant.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Cook;
import restaurant.model.Position;
import restaurant.model.Users;
import restaurant.model.Waiter;

import java.util.List;

public class UsersDao implements MainMethodDao<Users> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersDao.class);
    SessionFactory sessionFactory;
    private HibernateTransactionManager txManager;

    public void setTxManager(HibernateTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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
        Query query = session.createQuery("select u from Users u");
        return query.list();
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
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select u from Users u where u.id =:id");
        query.setParameter("id", id);
        query.setCacheable(true);
        query.setCacheRegion("users");
        return (Users) query.uniqueResult();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Users> findByName(String name) {
        LOGGER.info("Find all users by first name where name or part name " + name);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select u from Users u where u.firstName like :name");
        query.setParameter("name", "%" + name + "%");
        return query.list();
//        return usersDao.findByNameUser(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Waiter> allUsersWaiter() {
        LOGGER.info("Select all users waiter ");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select u from Users u where u.positionUser =:position");
        query.setParameter("position", Position.WAITER);
        return query.list();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Cook> allUsersCook() {
        LOGGER.info("Select all users cook ");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select u from Users u where u.positionUser =:position");
        query.setParameter("position", Position.COOK);
        return query.list();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Users> selectUsersOnlyName() {
        LOGGER.info("Select users only first name and last name!");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select u.firstName, u.lastName from Users u");
        return query.list();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Users> findUsersByNameOrLastName(String name) {
        String firstName = null;
        String lastName = null;
        Session session = sessionFactory.getCurrentSession();
        Query query = null;
        if (name.indexOf(" ") == -1) {
            query = session.createQuery("select u from Users u where u.firstName =:name OR u.lastName =:name");
            query.setParameter("name", name);
        } else {
            int end = name.indexOf(" ");
            firstName = name.substring(0, end);
            name.replaceAll(" ", "");
            lastName = name.substring(end + 1, name.length());
            query = session.createQuery("select u from Users u where u.firstName =:firstName AND u.lastName =:lastName");
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
        }
        LOGGER.info("Select all users by name or last name!");
        return query.list();
    }
}
