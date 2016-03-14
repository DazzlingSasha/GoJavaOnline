package CoursesEE.ModuleNumberOne;

import java.util.TreeSet;
import java.util.Set;


public class EfficiencyTreeSet {
    private int numberOfIterations = 0;
    Set<Integer> arr = new TreeSet<>();

    public EfficiencyTreeSet(int numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
    }

    public long timeAddToTreeSet() {
        long start = 0;
        start = -System.currentTimeMillis();
        for (int i = 0; i < numberOfIterations; i++) {
            arr.add(i);
        }
        start += System.currentTimeMillis();
        return start;
    }

    public long timeSearchInTreeSet(int indexElements) {
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


    public long timeRemoveWithTreeSet() {
        long start = 0;
        start = -System.currentTimeMillis();
        for (int i = 0; i < arr.size(); i++) {
            arr.remove(i);
        }
        start += System.currentTimeMillis();
        return start;
    }

    public long timeAddObjectsToTreeSet(String text) {
        long start = 0;
        Set<String> arrPopulate = new TreeSet<>();
        start = -System.currentTimeMillis();
        for (int i = 0; i < numberOfIterations; i++) {
            arrPopulate.add(text + i);
        }
        start += System.currentTimeMillis();
        return start;
    }
}
