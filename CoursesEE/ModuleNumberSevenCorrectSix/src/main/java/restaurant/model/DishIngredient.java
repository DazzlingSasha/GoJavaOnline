package restaurant.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ingredients_for_dish")
public class DishIngredient {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column
    private int id;

//    @ManyToOne
//    @Column(name = "id_dish")
//    private Dish idDish;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ingredients_for_dish",
    joinColumns = @JoinColumn(name = "id_dish"),
    inverseJoinColumns = @JoinColumn(name = "id_ingredient")
    )
    private List<Ingredient> idIngredient;

//    private String nameIngredient;

    @Column(name = "quantity")
    private double quantity;

    public DishIngredient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Ingredient> getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(List<Ingredient> idIngredient) {
        this.idIngredient = idIngredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    //    public Dish getIdDish() {
//        return idDish;
//    }
//
//    public void setIdDish(int idDish) {
//        this.idDish.setId(idDish);
//    }

    //    public List<Ingredient> getIdIngredient() {
//        return idIngredient.get(idDish.getId());
//    }
//
//    public void setIdIngredient(int idIngredient) {
//        this.idIngredient.setId(idIngredient);
//    }

//    public String getNameIngredient() {
//        return idIngredient.getName();
//    }
//
//    public void setNameIngredient(String nameIngredient) {
//        this.idIngredient.setName(nameIngredient);
//    }
}
