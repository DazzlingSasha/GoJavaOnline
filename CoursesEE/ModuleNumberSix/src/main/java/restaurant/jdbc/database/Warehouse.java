package restaurant.jdbc.database;

public class Warehouse {
    private int id;
    private int idIngredient;
    private String quantity;

    public void setId(int id) {
        this.id = id;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public String getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", idIngredient=" + idIngredient +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
