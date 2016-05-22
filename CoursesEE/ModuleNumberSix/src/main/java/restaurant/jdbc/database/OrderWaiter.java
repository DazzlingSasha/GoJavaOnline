package restaurant.jdbc.database;

import java.sql.Time;
import java.text.SimpleDateFormat;


public class OrderWaiter {
    private int id;
    private int id_user;
    private String idsDishes;
    private int numberTable;
    private String dateOrder;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getIdsDishes() {
        return idsDishes;
    }

    public void setIdsDishes(String idsDishes) {
        this.idsDishes = idsDishes;
    }

    public int getNumberTable() {
        return numberTable;
    }

    public void setNumberTable(int numberTable) {
        this.numberTable = numberTable;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void formatDateOrder(Time dateOrder) {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.dateOrder = f.format(dateOrder);
    }

    @Override
    public String toString() {
        return "Order {" +
                "id: " + id +
                ", user:  " + id_user +
                ", Dishes: '" + idsDishes + '\'' +
                ", Table №" + numberTable +
                ", Date order: " + dateOrder +
                '}';
    }
}
