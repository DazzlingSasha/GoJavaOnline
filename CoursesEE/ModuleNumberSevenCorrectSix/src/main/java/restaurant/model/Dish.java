package restaurant.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column()
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category")
    private Menu category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ingredients_for_dish",
            joinColumns = @JoinColumn(name = "id_dish"),
            inverseJoinColumns = @JoinColumn(name = "id_ingredient")
    )
    private List<Ingredient> idIngredient;

    @Column(name = "ids_ingredients_dish")
    private String ingredientsForDishes;

    @Column(name = "cost")
    private int cost;

    @Column(name = "weight")
    private int weight;

    public Dish() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Menu getCategory() {
        return category;
    }

    public String getNameCategory() {
        return category.getCategory();
    }

    public String getIngredientsForDishes() {
        StringBuilder sb = new StringBuilder();
        for(Ingredient item : idIngredient){
            sb.append(item.getName()).append("\n");
        }
        return sb.toString();
    }

    public int getCost() {
        return cost;
    }

    public int getWeight() {
        return weight;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Menu category) {
        this.category = category;
    }

    public void setNameCategory(String nameCategory) {
        this.category.setCategory(nameCategory);
    }

    public void setIngredientsForDishes(String ingredientsForDishes) {
        this.ingredientsForDishes = ingredientsForDishes;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Ingredient> getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(List<Ingredient> idIngredient) {
        this.idIngredient = idIngredient;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", ingredientsForDishes='" + ingredientsForDishes + '\'' +
                ", cost=" + cost + "grn" +
                ", weight=" + weight + "g " +
                '}';
    }
}
