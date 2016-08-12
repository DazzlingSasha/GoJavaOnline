package restaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import restaurant.service.UsersService;

import java.util.Map;

@Controller
public class UsersController {

    private UsersService usersService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Map<String, Object> model) {
        model.put("users", usersService.getUsers());
        return "users";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView users(@RequestParam("id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", usersService.getUserById(Integer.parseInt(id)));
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @Autowired
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }
}
