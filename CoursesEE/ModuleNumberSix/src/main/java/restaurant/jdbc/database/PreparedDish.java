package restaurant.jdbc.database;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class PreparedDish {
    private int id;
    private int idDish;
    private String nameDish;
    private String categoryDish;
    private int idUser;
    private String nameUser;
    private int idOrder;
    private Date datePreparedDish;
    private int prepared;

    public void setPrepared(int prepared) {
        this.prepared = prepared;
    }

    public int getPrepared() {
        return prepared;
    }

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

    public String getNameDish() {
        return nameDish;
    }

    public void setNameDish(String nameDish) {
        this.nameDish = nameDish;
    }

    public String getCategoryDish() {
        return categoryDish;
    }

    public void setCategoryDish(String categoryDish) {
        this.categoryDish = categoryDish;
    }

    public void setDatePreparedDish(Date datePreparedDish) {
        this.datePreparedDish = datePreparedDish;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public Date getDatePreparedDish() {
        return datePreparedDish;
    }

//    public void formatDatePreparedDish(Time datePreparedDish) {
//        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        this.datePreparedDish = f.format(datePreparedDish);
//    }

    @Override
    public String toString() {
        return "PreparedDish{" +
                "id=" + id +
                ", idDish=" + idDish +
                ", nameDish='" + nameDish + '\'' +
                ", categoryDish='" + categoryDish + '\'' +
                ", idUser=" + idUser +
                ", idOrder=" + idOrder +
                ", datePreparedDish='" + datePreparedDish + '\'' +
                '}';
    }
}
