package CoursesEE.ModuleNumberTwo;

import CoursesEE.ModuleNumberTwo.ModuleTowTaskOne.Task;

public class LongTask<Integer> implements Task<Integer> {
    private int index;
    private int result;

    public LongTask(int numberLong) {
        this.index = numberLong;
        execute();
    }

    @Override
    public void execute() {
        result = index + index;
    }

    @Override
    public Object getResult() {
        return result;
    }
}
