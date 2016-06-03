package restaurant.controllers;

import java.util.List;

public interface MainMethodControllers<T> {
    void addInDatabase(T item);

    List<T> selectAll();

    void deleteWithDatabase(int id);

    void updateInDatabase(T item);

    T findById(int id);

    List<T> findByName(String name);
}
