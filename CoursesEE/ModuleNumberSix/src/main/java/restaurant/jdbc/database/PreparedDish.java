package restaurant.jdbc.database;

import java.sql.Time;
import java.text.SimpleDateFormat;

public class PreparedDish {
    private int id;
    private int idDish;
    private String idUser;
    private int idOrder;
    private String datePreparedDish;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDish() {
        return idDish;
    }

    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getDatePreparedDish() {
        return datePreparedDish;
    }

    public void formatDatePreparedDish(Time datePreparedDish) {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.datePreparedDish = f.format(datePreparedDish);
    }

    @Override
    public String toString() {
        return "Prepared Dish{" +
                "id:" + id +
                ", Dish:" + idDish +
                ", User:" + idUser +
                ", Order:" + idOrder +
                ", Date prepared dish='" + datePreparedDish + '\'' +
                '}';
    }
}
