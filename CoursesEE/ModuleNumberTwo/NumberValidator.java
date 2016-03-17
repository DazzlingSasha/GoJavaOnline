package CoursesEE.ModuleNumberTwo;

import CoursesEE.ModuleNumberTwo.ModuleTowTaskOne.Validator;

public class NumberValidator<T extends Number> implements Validator<T> {
    private int greaterNumber;

    public NumberValidator(int i) {
        this.greaterNumber = i;
    }

    @Override
    public boolean isValid(Object result) {
        if (((T) result).intValue() > greaterNumber) {
            return true;
        } else {
            return false;
        }
    }
}
