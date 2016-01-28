package ModuleLesson8;

import java.util.*;


public class PrintAndSortCollection<T> {

    public void printList(List<T> list) {
        final int[] num = {0};
        System.out.printf("|\t%s\t|%40s|\n", "№",  "Name collection");
        list
                .stream()
                .forEach(index -> System.out.printf("|\t%s\t|%40s|\n", ++num[0], index));


    }

    public List<T> sortList(List<T> list) {
        for (int count = 0; count < list.size(); count++) {
            for (int index = 0; index < list.size() - 1; index++) {
                char[] first = list.get(index).toString().toLowerCase().toCharArray();
                char[] second = list.get(index + 1).toString().toLowerCase().toCharArray();

                if ((int) first[0] > (int) second[0]) {
                    swapIndex(list, index, index + 1);
                } else if ((int) first[0] == (int) second[0]) {
                    if (first.length > 1 && second.length > 1) {
                        if ((int) first[1] > (int) second[1]) {
                            swapIndex(list, index, index + 1);
                        }
                    }
                }
            }
        }
        return list;
    }

    public void swapIndex(List<T> list, int oneIndex, int twoIndex) {
        T index = list.get(oneIndex);
        list.set(oneIndex, list.get(twoIndex));
        list.set(twoIndex, index);
    }
}
