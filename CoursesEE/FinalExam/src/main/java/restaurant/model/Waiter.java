package restaurant.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Waiter extends Users {

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    @Fetch(FetchMode.SELECT)
    private List<OrderWaiter> order;

    public List<OrderWaiter> getOrder() {
        return order;
    }

    public void setOrder(List<OrderWaiter> order) {
        this.order = order;
    }
}
