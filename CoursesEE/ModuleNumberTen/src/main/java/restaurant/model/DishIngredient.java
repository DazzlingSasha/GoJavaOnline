package restaurant.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ingredients_for_dish")
public class DishIngredient {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column
    private int id;

//    @ManyToOne
    @Column(name = "id_dish")
    private int idDish;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ingredient")
    private Ingredient idIngredient;

//    private String nameIngredient;

    @Column(name = "quantity")
    private double quantity;

    public DishIngredient() {
    }

    public DishIngredient(Ingredient idIngredient, double quantity) {
        this.idIngredient = idIngredient;
        this.quantity = quantity;
    }

    public DishIngredient(int idDish, Ingredient idIngredient) {
        this.idDish = idDish;
        this.idIngredient = idIngredient;
    }

    public DishIngredient(int idDish, Ingredient idIngredient, double quantity) {
        this.idDish = idDish;
        this.idIngredient = idIngredient;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ingredient getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(Ingredient idIngredient) {
        this.idIngredient = idIngredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getIdDish() {
        return idDish;
    }

    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DishIngredient that = (DishIngredient) o;

        if (idDish != that.idDish) return false;
        return idIngredient != null ? idIngredient.equals(that.idIngredient) : that.idIngredient == null;

    }

    @Override
    public int hashCode() {
        int result = idDish;
        result = 31 * result + (idIngredient != null ? idIngredient.hashCode() : 0);
        return result;
    }
}
