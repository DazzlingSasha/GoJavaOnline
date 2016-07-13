package restaurant.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Waiter extends Users {

    @OneToMany()
    @JoinColumn(name = "id")
    private List<OrderWaiter> order;

    public List<OrderWaiter> getOrder() {
        return order;
    }

    public void setOrder(List<OrderWaiter> order) {
        this.order = order;
    }
}
