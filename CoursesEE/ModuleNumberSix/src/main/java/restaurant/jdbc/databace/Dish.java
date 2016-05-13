package restaurant.jdbc.databace;

public class Dish {
    private int id;
    private String name;
    private int category;
    private String ingredientsForDishes;
    private int cost;
    private int weight;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCategory() {
        return category;
    }

    public String getIngredientsForDishes() {
        return ingredientsForDishes;
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

    public void setCategory(int category) {
        this.category = category;
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
