package restaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import restaurant.service.MenuService;

import java.util.Map;

@Controller
public class MenuController {

private MenuService menuService;

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String users(Map<String, Object> model) {
        model.put("menu", menuService.getMenu());
        model.put("dishes", menuService.getDish());
        return "menu";
    }

    @RequestMapping(value = "/dish", method = RequestMethod.GET)
    public ModelAndView dish(@RequestParam("id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dish", menuService.getDishById(Integer.parseInt(id)));
        modelAndView.setViewName("dish");
        return modelAndView;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
}
