package restaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import restaurant.model.Menu;
import restaurant.service.DishService;
import restaurant.service.MenuService;

import java.util.Map;

@Controller
public class MenuController {

    private MenuService menuService;
    private DishService dishService;

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menu(Map<String, Object> model) {
        model.put("menu", menuService.getMenu());
        model.put("dishes", dishService.getDish());
        return "menu";
    }

    //-------------------------------------Admin------------------------------------------
    @RequestMapping(value = "/admin/menu", method = RequestMethod.GET)
    public String menuAdmin(Model model) {
        model.addAttribute("menu", new Menu());
        model.addAttribute("menus", this.menuService.getMenu());
        return "/admin/menu";
    }

    @RequestMapping(value = "/admin/menu/add", method = RequestMethod.POST)
    public String menuCreateAdmin(@ModelAttribute Menu menu) {
        if (menu.getId() == 0) {            //new menu, add it
            this.menuService.addMenu(menu);
        } else {                            //existing menu, call update
            this.menuService.updateMenu(menu);
        }
        return "redirect:/admin/menu";
    }

    @RequestMapping("/admin/menu/remove/{id}")
    public String removeMenu(@PathVariable("id") int id, Model model) {
        this.menuService.removeMenu(menuService.getMenuById(id));
        return "redirect:/admin/menu";
    }

    @RequestMapping("/admin/menu/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("menu", this.menuService.getMenuById(new Integer(id)));
        model.addAttribute("menus", this.menuService.getMenu());
        return "/admin/menu";
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }
}
