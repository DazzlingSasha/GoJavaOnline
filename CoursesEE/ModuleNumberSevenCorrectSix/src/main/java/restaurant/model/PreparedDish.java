package restaurant.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "prepared_dish")
public class PreparedDish {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_dish")
    private Dish idDish;

    //    private String nameDish;
//    private String categoryDish;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private Users idUser;

    //    private String nameUser;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_order")
    private OrderWaiter idOrder;

    @Column(name = "data_dish")
    private Date datePreparedDish;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prepared_dish")
    private Cook prepared;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dish getIdDish() {
        return idDish;
    }

    public void setIdDish(Dish dish) {
        this.idDish = dish;
    }

    public String getNameDish() {
        return idDish.getName();
    }

    public void setNameDish(String nameDish) {
        this.idDish.setName(nameDish);
    }

    public String getCategoryDish() {
        return idDish.getNameCategory();
    }

//    public void setCategoryDish(String categoryDish) {
//        new Menu().setCategory(categoryDish);
//    }

    public void setDatePreparedDish(Date datePreparedDish) {
        this.datePreparedDish = datePreparedDish;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users user) {
        this.idUser = user;
    }

    public String getNameUser() {
        return idUser.getFirstName();
    }

    public void setNameUser(String nameUser) {
        this.idUser.setFirstName(nameUser);
    }

    public OrderWaiter getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(OrderWaiter order) {
        this.idOrder = order;
    }

    public Date getDatePreparedDish() {
        return datePreparedDish;
    }

    public void setPrepared(Cook prepared) {
        this.prepared = prepared;
    }

    public Cook getPrepared() {
        return prepared;
    }

    @Override
    public String toString() {
        return "PreparedDish{" +
                "id=" + id +
                ", idDish=" + idDish +
                ", nameDish='" + idDish.getName() + '\'' +
                ", categoryDish='" + idDish.getCategory() + '\'' +
                ", idUser=" + idUser +
                ", idOrder=" + idOrder +
                ", datePreparedDish='" + datePreparedDish + '\'' +
                '}';
    }
}
