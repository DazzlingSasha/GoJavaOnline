package ModuleLesson5;

/**
 * Создать класс, который осуществляет поиск максимального и минимального элемента в массиве из целых чисел (int[]).
 */
public class SearchMinAndMaxValue {

    public SearchMinAndMaxValue(int[] array){

        int max = array[0];
        int min = array[0];
        for(int i =0; i< array.length; i++){
            if(max< array[i]){
                max = array[i];
            }
            if(min> array[i]){
                min= array[i];
            }
        }
        System.out.println(String.format("The array maximum value: %s, and minimum value: %s", max, min));
    }

//    public static void main(String[] args){
//        int[] arr = {-77, 2, 3222, 66, 777, -234, -7, 55, 999};
//        SearchMinAndMaxValue searchMinAndMaxValue = new SearchMinAndMaxValue(arr);
//    }
}
