package CoursesEE.ModuleNumberOne;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class EfficiencyLinkedList {
    private int numberOfIterations = 0;
    List<Integer> arr = new LinkedList<>();

    public EfficiencyLinkedList(int numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
    }

    public long timeAddToLinkedList() {
        long start;
        start = -System.currentTimeMillis();
        for (int i = 0; i < numberOfIterations; i++) {
            arr.add(i);
        }
        start += System.currentTimeMillis();
        return start;
    }

    public long timeGetWithLinkedList() {
        long start;
        start = -System.currentTimeMillis();
        for (int i = 0; i < numberOfIterations; i++) {
            arr.get(i);
        }
        start += System.currentTimeMillis();
        return start;
    }

    public long timeSearchInLinkedList(int indexElements) {
        long start;
        start = -System.currentTimeMillis();
        for (int i = 0; i < numberOfIterations; i++) {
            if( arr.contains(indexElements)){
                break;
            }
        }
        start += System.currentTimeMillis();
        return start;
    }

    LinkedList<Integer> copy = new LinkedList<>();

    public long timeAddIteratorToLinkedList() {
        Iterator<Integer> iterator = arr.iterator();
        long start;
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

    public long timeRemoveWithLinkedListIterator() {
        long start;
        start = -System.currentTimeMillis();
        Iterator<Integer> iterator = copy.iterator();
        while(iterator.hasNext()){
            iterator.next();
            iterator.remove();
        }
        start += System.currentTimeMillis();
        return start;
    }

    public long timeRemoveWithLinkedList() {
        long start;
        start = -System.currentTimeMillis();
        for (int i = 0; i < arr.size(); i++) {
            arr.remove(i);
        }
        start += System.currentTimeMillis();
        return start;
    }

    public long timeAddObjectsToLinkedList(String text) {
        long start;
        List<String> arrPopulate = new LinkedList<>();
        start = -System.currentTimeMillis();
        for (int i = 0; i < numberOfIterations; i++) {
            arrPopulate.add(text + i);
        }
        start += System.currentTimeMillis();
        return start;
    }
}
