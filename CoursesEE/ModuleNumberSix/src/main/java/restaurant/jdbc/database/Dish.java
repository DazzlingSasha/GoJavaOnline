package restaurant.jdbc.database;

public class Dish {

    private int id;
    private String name;
    private int category;
    private String nameCategory;

    private String ingredientsForDishes;

    private int cost;
    private int weight;
    public Dish() {
    }

    public Dish(int id, String name, int category, String ingredientsForDishes, int cost, int weight) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.ingredientsForDishes = ingredientsForDishes;
        this.cost = cost;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCategory() {
        return category;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
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
