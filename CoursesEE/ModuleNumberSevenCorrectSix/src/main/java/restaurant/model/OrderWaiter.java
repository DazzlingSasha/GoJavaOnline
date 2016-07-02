package restaurant.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

@Entity
@Table(name = "order_waiter")
public class OrderWaiter {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private Users id_user;


    @Column(name = "ids_dishes")
    private String idsDishes;

    @Column(name = "number_table")
    private int numberTable;

    @Column(name = "data_dish")
    private Date dateOrder;

    @Column(name = "open_close")
    private int closeOrOpenOrder;

    public int getId() {
        return id;
    }

    public Users getId_user() {
        return id_user;
    }

    public String getNameUser() {
        return id_user.getFirstName();
    }

    public String getIdsDishes() {
        return idsDishes.toString();
    }

    public int getNumberTable() {
        return numberTable;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public int getCloseOrOpenOrder() {
        return closeOrOpenOrder;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setId_user(Users user) {
        this.id_user = user;
    }

    public void setNameUser(String nameUser) {
        this.id_user.setFirstName(nameUser);
    }

    public void setIdsDishes(String idsDishes) {
        this.idsDishes = idsDishes;
    }

    public void setNumberTable(int numberTable) {
        this.numberTable = numberTable;
    }

    public void setCloseOrOpenOrder(int closeOrOpenOrder) {
        this.closeOrOpenOrder = closeOrOpenOrder;
    }

//    public void formatDateOrder(Time dateOrder) {
//        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        this.dateOrder = f.format(dateOrder);
//    }

    @Override
    public String toString() {
        return "OrderWaiter{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", nameUser='" + id_user.getFirstName()+ " " + id_user.getLastName() + '\'' +
                ", idsDishes='" + idsDishes + '\'' +
                ", numberTable=" + numberTable +
                ", dateOrder='" + dateOrder + '\'' +
                ", closeOrOpenOrder=" + closeOrOpenOrder +
                '}';
    }
}
