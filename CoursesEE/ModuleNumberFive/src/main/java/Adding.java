public class Adding implements Multitasking {

    public Adding() {
    }

//    @Override
//    public int execute(int a, int b) {
//        return a + b;
//    }
    @Override
    public Number execute(String a, String b) {
        ChooseType type = new ChooseType();
        Number first = type.ChooseTypeElement(a);
        Number second = type.ChooseTypeElement(b);
        Number result = 0;

        if (first instanceof Integer && second instanceof Integer) {
            result = first.intValue() + second.intValue();
        } else if (first instanceof Long && second instanceof Long) {
            result = first.longValue() + second.longValue();
        } else if (first instanceof Float && second instanceof Float) {
            result = first.floatValue() + second.floatValue();
        } else if (first instanceof Double && second instanceof Double) {
            result = first.floatValue() + second.floatValue();
        } else {
            System.out.println("Error: Enter same type numbers");
        }
        return result;
    }


//        if(Integer.parseInt(a) instanceof Integer && b instanceof Integer) {
//            return a.intValue() + b.intValue();
//        } else if(a instanceof Long && b instanceof Long){
//            return a.longValue() + b.longValue();
//        } else if(a instanceof Double && b instanceof Double){
//            return a.doubleValue() + b.doubleValue();
//        } else {
//            return a.floatValue() + b.floatValue();
//        }
//        int elementFirst = Integer.parseInt(a);
//
//        return indexA;
//    }


    @Override
    public String execute(String a) {
        return a;
    }

//    @Override
//    public double execute(double a, double b) {
//        return a + b;
//    }
//
//    @Override
//    public float execute(float a, float b) {
//        return a + b;
//    }
//
//    @Override
//    public long execute(long a, long b) {
//        return a + b;
//    }

//    @Override
//    public Number execute(Number a, Number b) {
////        if(a instanceof Integer && b instanceof Integer) {
//            return a.intValue() + b.intValue();
//        } else if(a instanceof Long && b instanceof Long){
//            return a.longValue() + b.longValue();
//        } else if(a instanceof Double && b instanceof Double){
//            return a.doubleValue() + b.doubleValue();
//        } else {
//            return a.floatValue() + b.floatValue();
//        }
//    }
}
