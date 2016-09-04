package restaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import restaurant.model.Dish;
import restaurant.model.Menu;
import restaurant.service.MenuService;

import java.util.List;
import java.util.Map;

@Controller
public class MenuController {

private MenuService menuService;

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    @ResponseBody
    public List<Menu> menus() {
        return menuService.getMenu();
    }

    @RequestMapping(value = "/menu/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Dish> dishesForMenu(@PathVariable String id){
        return menuService.getNameDishesByCategory(Integer.parseInt(id));
    }

    @RequestMapping(value = "/menu/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Menu findMenu(@PathVariable String name){
        return menuService.getFindMenuByName(name);
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
}
