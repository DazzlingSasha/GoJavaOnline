package CoursesEE.ModuleNumberTwo;

import CoursesEE.ModuleNumberTwo.ModuleTowTaskOne.Executor;

import java.util.ArrayList;
import java.util.List;

public class ExecutorImpl<Number> implements Executor<Number> {
    List<Number> listValid = new ArrayList<>();
    List<Number> listInvalid = new ArrayList<>();

    @Override
    public void addTask(ModuleTowTaskOne.Task task) {
        listValid.add((Number) task.getResult());
    }

    @Override
    public void addTask(ModuleTowTaskOne.Task task, ModuleTowTaskOne.Validator validator) {
        if (validator.isValid(task.getResult())) {
            if (task.getResult() == null) {
                throw new NullPointerException();
            }
            listValid.add((Number) task.getResult());
        } else {
            listInvalid.add((Number) task.getResult());
        }
    }

    @Override
    public void execute() {
        getValidResults();
    }

    @Override
    public List<Number> getValidResults() {
        for (int i = 0; i < listValid.size(); i++) {
            if (listValid.get(i) == null) {
                throw new NullPointerException();
            }
        }
        return listValid;
    }

    @Override
    public List<Number> getInvalidResults() {
        return listInvalid;
    }
}
