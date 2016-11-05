package restaurant.service;

import org.junit.Assert;
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
import restaurant.dao.IngredientDao;
import restaurant.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/hibernateContext.xml"})
@Transactional(propagation = Propagation.REQUIRED)
public class IngredientServiceTest {

    @Autowired
    private IngredientService ingredientService;

    @Mock
    private IngredientDao ingredientDao;

    private Ingredient ingredient;

    @Before
    public void setUp() throws Exception {
        ingredientDao = Mockito.mock(IngredientDao.class);
        ingredientService = new IngredientService();
        ingredientService.setIngredientDao(ingredientDao);

        ingredient = new Ingredient();
        ingredient.setId(2);
        ingredient.setName("banana");
        ingredient.setUnit("kg");
    }

    @Test
    public void doWorkTest() throws Exception {
        ingredientDao.selectAll();
        verify(ingredientDao).selectAll();
    }

    @Test
    public void testSetup() throws Exception {
        Assert.assertTrue(ingredientDao != null);
    }

    @Test
    public void testGetIngredient() throws Exception {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(new Ingredient());
        Ingredient ingredient2 = new Ingredient();
        ingredient2 = new Ingredient();
        ingredient2.setId(3);
        ingredient2.setName("orange");
        ingredient2.setUnit("kg");

        List<Ingredient> list = new ArrayList<>();
        list.add(ingredient2);
        list.add(ingredient);
        when(ingredientDao.selectAll()).thenReturn(list);

        assertTrue(ingredientService.getIngredient().equals(list));
    }

    @Test
    public void testGetById() throws Exception {
        when(ingredientDao.findById(2)).thenReturn(ingredient);
        Ingredient ingredient2 = ingredientService.getById(2);
        System.out.println(ingredient2);
        assertTrue(ingredient2.equals(ingredient));
    }

    @Test
    public void testCreateIngredient() throws Exception {
        when(ingredientService.getById(2)).thenReturn(ingredient);
        ingredientDao.addInDatabase(ingredient);
        ingredientService.createIngredient(ingredient);
        Ingredient mDoc = ingredientService.getById(2);
        assertNotNull(mDoc);
        assertTrue(mDoc.getId() == ingredient.getId());
    }

    @Test
    public void testRemoveIngredient() throws Exception {
        ingredientService.createIngredient(ingredient);

        ingredientService.removeIngredient(ingredientService.getById(2));
        assertNull(ingredientService.getById(2));
    }
}