package restaurant.jdbc.database;

import java.sql.Time;
import java.text.SimpleDateFormat;


public class OrderWaiter {
    private int id;
    private int id_user;
    private String nameUser;
    private String idsDishes;
    private int numberTable;
    private String dateOrder;
    private int closeOrOpenOrder;

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

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
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

    public int getCloseOrOpenOrder() {
        return closeOrOpenOrder;
    }

    public void setCloseOrOpenOrder(int closeOrOpenOrder) {
        this.closeOrOpenOrder = closeOrOpenOrder;
    }

    @Override
    public String toString() {
        return "OrderWaiter{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", nameUser='" + nameUser + '\'' +
                ", idsDishes='" + idsDishes + '\'' +
                ", numberTable=" + numberTable +
                ", dateOrder='" + dateOrder + '\'' +
                ", closeOrOpenOrder=" + closeOrOpenOrder +
                '}';
    }
}
