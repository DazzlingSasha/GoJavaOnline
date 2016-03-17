package CoursesEE.ModuleNumberTwo;

import java.util.List;

/*
Домашнее задание к Модулю #2
Написать фреймворк для последоватьного выполнения задач:
1 Переписать интерфейс Task так что бы он был типизирован по результату (значению возращаемуому методом getResult()).
2 Переписать интерфейс Validator так что бы он был типизирован по принемаемому значению isValid(Object result);
3 Переписать интерфейс Executor так чтоб он был типизирован в соответсвии с с типизацией Task и Validator
4 Импелементирвать интерфейс Executor
5 Написать к нему тесты.
* */
public class ModuleTowTaskOne {

    public interface Executor<E> {

        // Добавить таск на выполнение. Результат таска будет доступен через метод getValidResults().
        // Бросает Эксепшн если уже был вызван метод execute()
        void addTask(Task task);

        // Добавить таск на выполнение и валидатор результата. Результат таска будет записан в ValidResults если validator.isValid вернет true для этого результата
        // Результат таска будет записан в InvalidResults если validator.isValid вернет false для этого результата
        // Бросает Эксепшн если уже был вызван метод execute()
        void addTask(Task task, Validator validator);

        // Выполнить все добавленые таски
        void execute();

        // Получить валидные результаты. Бросает Эксепшн если не был вызван метод execute()
        List<E> getValidResults();

        // Получить невалидные результаты. Бросает Эксепшн если не был вызван метод execute()
        List<E> getInvalidResults();
    }

    public interface Task<T> {
        // Метода запускает таск на выполнение
        void execute();

        // Возвращает результат выполнения
        Object getResult();
    }

    public interface Validator<E> {
        // Валидирует переданое значение
        boolean isValid(Object result);
    }

}
