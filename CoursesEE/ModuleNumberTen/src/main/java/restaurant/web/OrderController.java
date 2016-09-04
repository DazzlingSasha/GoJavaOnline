package restaurant.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import restaurant.model.OrderWaiter;
import restaurant.service.OrderService;

import java.util.List;

@Controller
public class OrderController {

    private OrderService orderService;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderWaiter> findAllOrder(){
        return orderService.getAllOrders();
    }

    @RequestMapping(value = "/order/close", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderWaiter> findCloseOrder(){
        return orderService.getCloseOrders();
    }

    @RequestMapping(value = "/order/open", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderWaiter> findOpenOrder(){
        return orderService.getOpenOrders();
    }

    @RequestMapping(value = "/order/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public OrderWaiter findOrderById(@PathVariable String id){
        return orderService.getOrder(Integer.parseInt(id));
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
