package restaurant.web;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import restaurant.model.Dish;
import restaurant.model.DishIngredient;
import restaurant.model.Ingredient;
import restaurant.model.Menu;
import restaurant.service.DishService;
import restaurant.service.IngredientService;
import restaurant.service.MenuService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DishController {

    private DishService dishService;
    private MenuService menuService;
    private IngredientService ingredientService;

    public DishController() {
    }

    @RequestMapping(value = "/dish", method = RequestMethod.GET)
    public ModelAndView dish(@RequestParam("id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dish", dishService.getDishById(Integer.parseInt(id)));
        modelAndView.setViewName("dish");
        return modelAndView;
    }

    //-------------------------------------Admin------------------------------------------
    @RequestMapping(value = "/admin/dish", method = RequestMethod.GET)
    public String dishAdmin(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("dishes", this.dishService.getDish());
        model.addAttribute("menus", this.menuService.getMenu());
        model.addAttribute("ingredients", this.ingredientService.getIngredient());
        model.addAttribute("dishIngredients", this.dishService.getIngredientsByAllDishes());
        return "/admin/dish";
    }

    @RequestMapping(value = "/admin/dish/add", method = RequestMethod.POST)
    public String dishCreateAdmin(@ModelAttribute("dish") Dish dish, @RequestParam int menu_id) {
        System.out.println(dish.toString());
        System.out.println(menu_id);

        Menu menu = menuService.getMenuById(menu_id);
        if (dish.getId() == 0) {
            System.out.println("dish zero");
            //new menu, add it
            dish.setCategory(menu);
            this.dishService.addDish(dish);
        } else {
            //existing menu, call update
            dish.setCategory(menu);
            this.dishService.updateDish(dish);
        }
        return "redirect:/admin/dish";
    }

    @RequestMapping("/admin/dish/remove/{id}")
    public String removeDish(@PathVariable("id") int id, Model model) {
        this.dishService.removeDish(dishService.getDishById(id));
        return "redirect:/admin/dish";
    }

    @RequestMapping("/admin/dish/edit/{id}")
    public String editDish(@PathVariable("id") int id, Model model) {
        model.addAttribute("dish", this.dishService.getDishById(new Integer(id)));
        model.addAttribute("dishes", this.dishService.getDish());
        model.addAttribute("menus", this.menuService.getMenu());
        model.addAttribute("ingredients", this.ingredientService.getIngredient());
        return "/admin/dish";
    }

    @RequestMapping(value = "/admin/dish/add_ingredient/{id}", method = RequestMethod.POST)
    public String addIngredientForDish(@PathVariable("id") int idDish, @RequestParam int ingredient, @RequestParam double quantity) {
        DishIngredient dishIngredient = new DishIngredient(idDish, ingredientService.getById(ingredient), quantity);
        dishService.addIngredientForDish(dishIngredient);
        return "redirect:/admin/dish";
    }

    @RequestMapping(value = "/admin/dish/delete/{idIngredient}", method = RequestMethod.GET)
    public String deleteIngredientForDish(@PathVariable("idIngredient") int idIngredient) {
        dishService.deleteIngredientForDish(idIngredient);
        return "redirect:/admin/dish";
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setIngredientService(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
}
