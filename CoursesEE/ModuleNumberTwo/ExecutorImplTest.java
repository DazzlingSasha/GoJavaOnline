package CoursesEE.ModuleNumberTwo;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class ExecutorImplTest {

    static ModuleTowTaskOne myClass;
    static List<LongTask<Integer>> list;
    static List actualValid;
    static List actualInvalid;
    static ExecutorImpl<Number> executor;

    @Before
    public void setUp() {
        myClass = new ModuleTowTaskOne();
        list = new ArrayList<>();
        actualValid = new ArrayList<>();
        actualInvalid = new ArrayList<>();
        executor = new ExecutorImpl<>();

        LongTask<Integer> a1 = new LongTask<>(2);
        LongTask<Integer> a2 = new LongTask<>(11);
        LongTask<Integer> a3 = new LongTask<>(122);
        LongTask<Integer> a4 = new LongTask<>(-122);

        list.add(a1);
        list.add(a2);
        list.add(a3);
        list.add(a4);
    }

    @Test
    public void testAddTask() throws Exception {
        for (LongTask<Integer> index : list) {
            executor.addTask(index);
        }

        actualValid = executor.getValidResults();
        actualInvalid = executor.getInvalidResults();

        int i = 0;

        for (Object index : actualValid) {
            Assert.assertEquals("The loop in testAddTask in array actualValid", index, list.get(i).getResult());
            i++;
        }

        Assert.assertEquals("The testAddTask have zero value in array actualInvalid", actualInvalid.size(), 0);
    }

    @Test
    public void testAddTaskWithValidator() throws Exception {

        NumberValidator validator = new NumberValidator(40);

        for (LongTask<Integer> index : list) {
            executor.addTask(index, validator);
        }
        actualValid = executor.getValidResults();
        actualInvalid = executor.getInvalidResults();

        int i = 0;

        for (Object index : actualValid) {
            Assert.assertEquals("The loop in testAddTaskWithValidator", index, 244);
            i++;
        }
        Assert.assertEquals("The testAddTaskWithValidator have 1 value in array actualValid", actualValid.size(), 1);
        Assert.assertEquals("The testAddTaskWithValidator have 3 value in array actualInvalid", actualInvalid.size(), 3);
        int valid = 20;
        int value = 10;
        executor.addTask(new LongTask<Integer>(10), new NumberValidator<Integer>(valid));
        Assert.assertEquals("The testAddTaskWithValidator. After the addition of the new value = " + value
                + " and validator = " + valid + " added to the array actualInvalid", actualInvalid.size(), 4);
    }


}