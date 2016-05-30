package restaurant.jdbc.database;

public class Warehouse {
    private int id;
    private int idIngredient;
    private double quantity;
    private String unit;

    public void setId(int id) {
        this.id = id;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public int getId() {
        return id;
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    @Override
    public String toString() {
        return "Warehouse {" +
                "id:" + id +
                ", idIngredient= " + idIngredient +
                ", quantity: " + quantity +
                " " + unit + "}";
    }
}
