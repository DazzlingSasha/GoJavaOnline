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

    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }
}
