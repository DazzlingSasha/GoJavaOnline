package restaurant.jdbc.databace;

public class Menu {
    private int id;
    private String category;
    private String listDishes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getListDishes() {
        return listDishes;
    }

    public void setListDishes(String listDishes) {
        this.listDishes = listDishes;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", listDishes='" + listDishes + '\'' +
                '}';
    }
}
