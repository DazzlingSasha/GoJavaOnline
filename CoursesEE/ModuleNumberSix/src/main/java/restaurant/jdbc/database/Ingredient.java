package restaurant.jdbc.database;

public class Ingredient {
    private int id;
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Ingredient:{" +
                "id: " + id +
                ", name: '" + name + "}";
    }
}
