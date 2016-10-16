package restaurant.dao;

import java.util.List;

public interface MainMethodDao<T> {
    void addInDatabase(T item);

    List<T> selectAll();

    void deleteWithDatabase(T id);

    void updateInDatabase(T item);

    T findById(int id);

    List<T> findByName(String name);
}
