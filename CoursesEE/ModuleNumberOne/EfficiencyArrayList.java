package CoursesEE.ModuleNumberOne;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EfficiencyArrayList {
    private int numberOfIterations = 0;
    List<Integer> arr = new ArrayList<>();

    public EfficiencyArrayList(int numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
    }

    public long timeAddToArrayList() {
        long start = 0;
        start = -System.currentTimeMillis();
        for (int i = 0; i < numberOfIterations; i++) {
            arr.add(i);
        }
        start += System.currentTimeMillis();
        return start;
    }

    public long timeGetWithArrayList() {
        long start = 0;
        start = -System.currentTimeMillis();
        for (int i = 0; i < numberOfIterations; i++) {
            arr.get(i);
        }
        start += System.currentTimeMillis();
        return start;
    }

    public long timeSearchInArrayList(int indexElements) {
        long start = 0;
        start = -System.currentTimeMillis();
        for (int i = 0; i < numberOfIterations; i++) {
           if( arr.contains(indexElements)){
               break;
           }
        }
        start += System.currentTimeMillis();
        return start;
    }

    ArrayList<Integer> copy = new ArrayList<>();;

    public long timeAddIteratorToArrayList() {
        Iterator<Integer> iterator = arr.iterator();
        long start = 0;
        start = -System.currentTimeMillis();
        while(iterator.hasNext()){
            copy.add(iterator.next());
        }
        start += System.currentTimeMillis();
        return start;
    }
    public void ddd(){
        for (int f : copy){
            System.out.println(f);
        }
    }

    public long timeRemoveWithArrayListIterator() {
        long start = 0;
        start = -System.currentTimeMillis();
        Iterator<Integer> iterator = copy.iterator();
        while(iterator.hasNext()){
            iterator.next();
            iterator.remove();
        }
        start += System.currentTimeMillis();
        return start;
    }

    public long timeRemoveWithArrayList() {
        long start = 0;
        start = -System.currentTimeMillis();
        for (int i = 0; i < arr.size(); i++) {
            arr.remove(i);
        }
        start += System.currentTimeMillis();
        return start;
    }

    public long timeAddObjectsToArrayList(String text) {
        long start = 0;
        List<String> arrPopulate = new ArrayList<>();
        start = -System.currentTimeMillis();
        for (int i = 0; i < numberOfIterations; i++) {
            arrPopulate.add(text + i);
        }
        start += System.currentTimeMillis();
        return start;
    }
}
