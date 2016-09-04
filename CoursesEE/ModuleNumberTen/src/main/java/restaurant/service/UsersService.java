package restaurant.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.dao.UsersDao;
import restaurant.model.Users;

import java.util.List;

public class UsersService {

    private UsersDao usersDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Users> getUsers(){
        return usersDao.selectAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Users getUserById(int id){
        return usersDao.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Users> getUsersName(){
        return usersDao.selectUsersOnlyName();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Users> getUserByNameOrLastName(String name){
        return usersDao.findUsersByNameOrLastName(name);
    }
    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }
}
