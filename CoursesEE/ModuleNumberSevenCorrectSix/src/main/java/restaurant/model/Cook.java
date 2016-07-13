package restaurant.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity
public class Cook  extends Users{

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "prepared_dish")
    @Fetch(FetchMode.SELECT)
    private List<PreparedDish> cookedDishes;

    public List<PreparedDish> getCookedDishes() {
        return cookedDishes;
    }

    public void setCookedDishes(List<PreparedDish> cookedDishes) {
        this.cookedDishes = cookedDishes;
    }
}
