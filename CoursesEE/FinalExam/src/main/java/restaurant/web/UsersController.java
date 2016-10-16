package restaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import restaurant.model.Position;
import restaurant.model.Users;
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
    //-------------------------------------Admin------------------------------------------

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String userAdmin(Model model) {
        model.addAttribute("user", new Users());
        model.addAttribute("users", this.usersService.getUsers());
        model.addAttribute("position", Position.values());
        return "/admin/users";
    }

    @RequestMapping(value = "/admin/user/add", method = RequestMethod.POST)
    public String userCreateAndUpdate(@ModelAttribute Users user) {
        if (user.getId() == 0) {            //new menu, add it
            this.usersService.addUser(user);
        } else {                            //existing menu, call update
            this.usersService.updateUser(user);
        }
        return "redirect:/admin/users";
    }

    @RequestMapping("/admin/user/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", this.usersService.getUserById(id));
        model.addAttribute("users", this.usersService.getUsers());
        model.addAttribute("position", Position.values());
        return "/admin/users";
    }

    @RequestMapping("/admin/user/remove/{id}")
    public String removeUser(@PathVariable("id") int id, Model model) {
        this.usersService.removeUser(usersService.getUserById(id));
        return "redirect:/admin/users";
    }

    @Autowired
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }
}
