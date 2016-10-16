package restaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import restaurant.model.Ingredient;
import restaurant.model.Warehouse;
import restaurant.service.IngredientService;
import restaurant.service.WarehouseService;

@Controller
public class WarehouseController {

    private WarehouseService warehouseService;
    private IngredientService ingredientService;

    public WarehouseController() {
    }
    //-------------------------------------Admin------------------------------------------

    @RequestMapping(value = "/admin/warehouse", method = RequestMethod.GET)
    public String warehouseAdmin(Model model) {
        model.addAttribute("warehouse", new Warehouse());
        model.addAttribute("warehouses", this.warehouseService.getWarehouse());
        model.addAttribute("ingredient", new Ingredient());
        return "/admin/warehouse";
    }

    @RequestMapping(value = "/admin/warehouse", method = RequestMethod.POST)
    public String searchAdmin(Model model, @RequestParam String name) {
        model.addAttribute("warehouse", new Warehouse());
        model.addAttribute("warehouses", this.warehouseService.getSearchWarehouse(name));
        model.addAttribute("ingredient", new Ingredient());
        return "/admin/warehouse";
    }

    @RequestMapping(value = "/admin/warehouse/edit/{id}", method = RequestMethod.POST)
    public String searchAdmin(@PathVariable("id") int id, @RequestParam String quantity) {
        Warehouse warehouse = warehouseService.findWarehouseById(id);
        double qual = Double.parseDouble(quantity);
        warehouse.setQuantity(qual);
        warehouseService.updateQuantity(warehouse);
        return "redirect:/admin/warehouse";
    }

    @RequestMapping(value = "/admin/warehouse/add", method = RequestMethod.POST)
    public String addIngredients(@ModelAttribute("ingredient") Ingredient ingredient) {
        ingredientService.createIngredient(ingredient);
        Ingredient itemIngredient = ingredientService.getById(ingredient.getId());
        warehouseService.createWarehouse(itemIngredient);
        return "redirect:/admin/warehouse";
    }

    @RequestMapping(value = "/admin/warehouse/delete/{id}", method = RequestMethod.GET)
    public String removeIngredients(@PathVariable("id") int id) {
        Warehouse warehouse = warehouseService.findWarehouseById(id);
        Ingredient ingredient = warehouse.getIdIngredient();
        warehouseService.removeWarehouse(warehouse);
        ingredientService.removeIngredient(ingredient);
        return "redirect:/admin/warehouse";
    }


    @Autowired
    public void setWarehouseService(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Autowired
    public void setIngredientService(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
}
