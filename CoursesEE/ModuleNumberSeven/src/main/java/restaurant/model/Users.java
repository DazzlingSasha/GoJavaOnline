package restaurant.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "position_user")
    private Position positionUser;

    @Column(name = "salary")
    private int salary;

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Position getPositionUser() {
        return positionUser;
    }

    public void setPositionUser(Position positionUser) {
        this.positionUser = positionUser;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", First Name='" + firstName + '\'' +
                ", Last Name='" + lastName + '\'' +
                ", Birthday=" + birthday +
                ", Phone='" + phone + '\'' +
                ", Position User='" + positionUser + '\'' +
                ", Salary=" + salary +" grn"+
                '}';
    }
}
