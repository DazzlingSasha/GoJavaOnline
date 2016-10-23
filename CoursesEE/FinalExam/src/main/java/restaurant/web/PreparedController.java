package restaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import restaurant.model.PreparedDish;
import restaurant.service.PreparedService;

import java.sql.Date;
import java.text.DateFormat;

@Controller
public class PreparedController {
    private PreparedService preparedService;
//--------------------------------- Admin -------------------------------------
    @RequestMapping(value = "/admin/history", method = RequestMethod.GET)
    public String getAllDishes(Model model){
        model.addAttribute("itemOrder", new PreparedDish());
        model.addAttribute("history", preparedService.getAllDishes());
        return "/admin/history";
    }

    @RequestMapping(value = "/admin/history/table", method = RequestMethod.GET)
    public String searchTable(Model model, @RequestParam int numberTable) {
        System.out.println(numberTable);
        model.addAttribute("history", this.preparedService.getSearchTable(numberTable));
        return "/admin/history";
    }

    @RequestMapping(value = "/admin/history/name", method = RequestMethod.POST)
    public String searchWaiter(Model model, @RequestParam String nameWaiter) {
        model.addAttribute("history", this.preparedService.getSearchWaiter(nameWaiter));
        return "/admin/history";
    }

    @RequestMapping(value = "/admin/history/date", method = RequestMethod.GET)
    public String searchDateTakeOrder(Model model, @RequestParam String date) {
        model.addAttribute("history", this.preparedService.getSearchDateTakeOrder(date));
        return "/admin/history";
    }


    @Autowired
    public void setPreparedService(PreparedService preparedService) {
        this.preparedService = preparedService;
    }
}
