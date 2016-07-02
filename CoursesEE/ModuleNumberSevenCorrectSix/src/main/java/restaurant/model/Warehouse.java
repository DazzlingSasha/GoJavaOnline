package restaurant.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @OneToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ingredient")
    private Ingredient idIngredient;

    @Column(name = "quantity")
    private double quantity;

//    @Column(name = "unit")
//    private String unit;

    public int getId() {
        return id;
    }

    public Ingredient getIdIngredient() {
        return idIngredient;
    }

    public String getItemWithDatabaseIngredients() {
        return idIngredient.getName();
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return idIngredient.getUnit();
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setIdIngredient(Ingredient idIngredient) {
        this.idIngredient = idIngredient;
    }

    public void setItemWithDatabaseIngredients(String itemWithDatabaseIngredients) {
        idIngredient.setName(itemWithDatabaseIngredients);
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

//    public void setUnit(String unit) {
//        this.unit = unit;
//    }

    @Override
    public String toString() {
        return "Warehouse {" +
                "id:" + id +
                ", idIngredient= " + idIngredient +
                ", quantity: " + quantity ;
    }
}
