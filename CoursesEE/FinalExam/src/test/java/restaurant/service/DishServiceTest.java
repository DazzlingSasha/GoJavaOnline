package restaurant.service;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.dao.DishDao;
import restaurant.model.Dish;
import restaurant.model.DishIngredient;
import restaurant.model.Ingredient;
import restaurant.model.Menu;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/hibernateContext.xml"})
@Transactional(propagation = Propagation.REQUIRED)
public class DishServiceTest {

    @Autowired
    private DishService dishService;

    @Autowired
    private IngredientService ingredientService;

    @Mock
    private DishDao dishDao;

    private Menu menu;
    private Ingredient ingredient;
    private Dish dish;

    @Before
    public void setUp() throws Exception {
        dishDao = Mockito.mock(DishDao.class);
        dishService = new DishService();
        dishService.setDishDao(dishDao);

        menu = new Menu();
        ingredient = new Ingredient();
        ingredient.setName("one banana");
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient);

        dish = new Dish();
        dish.setId(4);
        dish.setName("Banana");
        dish.setCategory(menu);
        dish.setNameCategory("ddd");
        dish.setIdIngredient(ingredientList);
        dish.setCost(100);
        dish.setWeight(200);
    }

    @Test
    public void testSetup() throws Exception {
        assertTrue(dishDao != null);
    }


    @Test
    public void testAddDish() throws Exception {
        when(dishService.getDishById(4)).thenReturn(dish);
        dishDao.addInDatabase(dish);
        dishService.addDish(dish);
        Dish mDoc = dishService.getDishById(4);
        assertNotNull(mDoc);
        assertTrue(mDoc.getIdIngredient().equals(dish.getIdIngredient()));
    }

    @Test
    public void testUpdateDish() throws Exception {
        when(dishDao.findById(3)).thenReturn(dish);
        Dish findDish = dishService.getDishById(3);
        findDish.setCost(300);
        dishService.updateDish(findDish);

        assertTrue(dish.getCost() == findDish.getCost());
    }

    @Test
    public void testRemoveDish() throws Exception {
        dishService.addDish(dish);

        dishService.removeDish(dishService.getDishById(4));
        assertNull(dishService.getDishById(4));
    }

    @Test
    public void testGetDish() throws Exception {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(new Ingredient());

        Dish dish1 = new Dish();
        dish1.setId(4);
        dish1.setName("Banana");
        dish1.setCategory(menu);
        dish1.setNameCategory("ddd");
        dish1.setIdIngredient(ingredientList);
        dish1.setCost(100);
        dish1.setWeight(200);

        List<Dish> list = new ArrayList<>();
        list.add(dish);
        list.add(dish1);
        when(dishDao.selectAll()).thenReturn(list);

        assertTrue(dishService.getDish().equals(list));
    }

    @Test
    public void testGetDishById() throws Exception {
        when(dishDao.findById(4)).thenReturn(dish);
        Dish dishTwo = dishService.getDishById(4);
        System.out.println(dishTwo);
        assertTrue(dishTwo.equals(dish));
    }

    @Test
    public void testAddIngredientForDish() throws Exception {
        Ingredient one = new Ingredient();
        one.setId(2);
        one.setName("one");
        one.setUnit("kg");

        Ingredient two = new Ingredient();
        two.setId(5);
        two.setName("two");
        two.setUnit("gram");

        DishIngredient dishIngredient = new DishIngredient();
        dishIngredient.setId(2);
        dishIngredient.setIdDish(4);
        dishIngredient.setIdIngredient(one);
        dishIngredient.setQuantity(2.2);

        DishIngredient dishIngredientTwo = new DishIngredient();
        dishIngredientTwo.setId(3);
        dishIngredientTwo.setIdDish(4);
        dishIngredientTwo.setIdIngredient(two);
        dishIngredientTwo.setQuantity(12.2);

        List<DishIngredient> list = new ArrayList<>();
        list.add(dishIngredient);
//        list.add(dishIngredientTwo);

        dishService.addIngredientForDish(dishIngredient);

        when(dishDao.getAllIngredientsForDishes()).thenReturn(list);
        when(dishDao.findInDishIngredient(dishIngredientTwo)).thenReturn(dishIngredientTwo);

        dishService.addIngredientForDish(dishService.getOneIngredientDish(5));
        List<DishIngredient> mDoc = dishService.getIngredientsByAllDishes();

        assertNotNull(mDoc);
        System.out.println(mDoc.size());

        assertTrue(mDoc.get(1).equals(dishService.getOneIngredientDish(3)) );
    }

    @Test
    public void testDeleteIngredientForDish() throws Exception {
        Ingredient one = new Ingredient();
        one.setId(2);
        one.setName("one");
        one.setUnit("kg");

        Ingredient two = new Ingredient();
        two.setId(5);
        two.setName("two");
        two.setUnit("gram");

        DishIngredient dishIngredient = new DishIngredient();
        dishIngredient.setId(2);
        dishIngredient.setIdDish(4);
        dishIngredient.setIdIngredient(one);
        dishIngredient.setQuantity(2.2);

        dishService.addIngredientForDish(dishIngredient);

        dishService.deleteIngredientForDish(2);
        assertNull(dishService.getOneIngredientDish(2));
    }

    @Test
    public void testGetIngredientsByAllDishes() throws Exception {
        Ingredient one = new Ingredient();
        one.setId(2);
        one.setName("one");
        one.setUnit("kg");

        Ingredient two = new Ingredient();
        two.setId(5);
        two.setName("two");
        two.setUnit("gram");

        DishIngredient dishIngredient = new DishIngredient();
        dishIngredient.setId(2);
        dishIngredient.setIdDish(4);
        dishIngredient.setIdIngredient(one);
        dishIngredient.setQuantity(2.2);

        DishIngredient dishIngredientTwo = new DishIngredient();
        dishIngredientTwo.setId(3);
        dishIngredientTwo.setIdDish(4);
        dishIngredientTwo.setIdIngredient(two);
        dishIngredientTwo.setQuantity(12.2);

        List<DishIngredient> list = new ArrayList<>();
        list.add(dishIngredient);
        list.add(dishIngredientTwo);

        dishService.addIngredientForDish(dishIngredient);
        dishService.addIngredientForDish(dishIngredientTwo);

        when(dishDao.getAllIngredientsForDishes()).thenReturn(list);

        dishDao.addInDishIngredient(dishIngredientTwo);
        List<DishIngredient> mDoc = dishService.getIngredientsByAllDishes();

        assertNotNull(mDoc);
        System.out.println(mDoc.size());

        assertTrue(mDoc.size() == 2);
    }

    @Test
    public void testGetOneIngredientDish() throws Exception {

    }

    @Test
    public void testGetDishByName() throws Exception {

    }

}