public class Subtracting implements Multitasking {
    public Subtracting() {
    }
    @Override
    public Number execute(String a, String b) {
        ChooseType type = new ChooseType();
        Number first = type.ChooseTypeElement(a);
        Number second = type.ChooseTypeElement(b);
        Number result = 0;

        if (first instanceof Integer && second instanceof Integer) {
            result = first.intValue() - second.intValue();
        } else if (first instanceof Long && second instanceof Long) {
            result = first.longValue() - second.longValue();
        } else if (first instanceof Float && second instanceof Float) {
            result = first.floatValue() - second.floatValue();
        } else if (first instanceof Double && second instanceof Double) {
            result = first.floatValue() - second.floatValue();
        } else {
            System.out.println("Error: Enter same type numbers");
        }
        return result;
    }
    @Override
    public String execute(String a) {
        return null;
    }
}

