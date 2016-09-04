package restaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import restaurant.model.Users;
import restaurant.service.UsersService;

import java.util.List;
import java.util.Map;

@Controller
public class UsersController {

    private UsersService usersService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public List<Users> users() {
        return usersService.getUsers();
    }

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Users> usersName() {
        return usersService.getUsersName();
    }


    @RequestMapping(value = "/user/find/{name}", method = RequestMethod.GET)
    @ResponseBody
    public List<Users> usersNameOrLastName(@PathVariable String name) {
        return usersService.getUserByNameOrLastName(name);
    }

    @RequestMapping(value = "/user/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Users users(@PathVariable String id) {
        return usersService.getUserById(Integer.parseInt(id));
    }

    @Autowired
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }
}
