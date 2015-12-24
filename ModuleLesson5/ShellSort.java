package ModuleLesson5;

/**
 * Сортировка массива алгоритмом ShellSort и реализовать его
 */
public class ShellSort {
    private int step = 0;
    private int maxValue = 0;
    private int minValue = 0;

    public int[] sort(int[] array){

        boolean isInterrupt = true;

        try {
            int searchStep = array.length / 2;
            int[] arrayStep = {1, 3, 5, 9, 17, 33, 65};

            int i = 0;
            while (arrayStep[i] < array.length / 2) {
                step = i;
                i++;
            }

            while (step >= 0 && array.length>1) {
                int indexNext = 0;
                while (isInterrupt) {
                    int[] arrayWithThreeValues = new int[3];
                    if (2 * arrayStep[step] + indexNext < array.length) {
                        arrayWithThreeValues[0] = array[indexNext];
                        arrayWithThreeValues[1] = array[arrayStep[step] + indexNext];
                        arrayWithThreeValues[2] = array[(2 * arrayStep[step] + indexNext)];
                        maxValue = arrayWithThreeValues[0];
                        minValue = arrayWithThreeValues[0];

                        for (int iterator : arrayWithThreeValues) {
                            if (maxValue < iterator)
                                maxValue = iterator;
                            if (minValue > iterator)
                                minValue = iterator;
                        }

                        for (int iterator : arrayWithThreeValues) {
                            if (iterator != minValue && iterator != maxValue) {
                                array[indexNext] = minValue;
                                array[arrayStep[step] + indexNext] = iterator;
                                array[2 * arrayStep[step] + indexNext] = maxValue;
                            }
                        }

                    } else {
                        if (array[arrayStep[step] + indexNext] < array[indexNext]) {
                            maxValue = array[indexNext];
                            array[indexNext] = array[arrayStep[step] + indexNext];
                            array[arrayStep[step] + indexNext] = maxValue;
                        }
                        isInterrupt = false;
                    }
                    indexNext++;
                }
                isInterrupt = true;
                step--;
            }


        }catch(NullPointerException e){
            System.out.println("The array is equals null");
        }
        return array;
    }


//    public static void main(String[] args){
//        int[] array = {-77, 2, 3222, 66, 777, -234, -7, 55, -999, 355, 456, 2345, 11};
//        ShellSort s = new ShellSort();
//        s.sort(array);
//        for(int h : array) {
//            System.out.print(h+" ");
//        }
//    }
}
