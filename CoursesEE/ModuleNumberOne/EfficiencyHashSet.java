package CoursesEE.ModuleNumberOne;


import java.util.HashSet;
import java.util.Set;


public class EfficiencyHashSet {
    private int numberOfIterations = 0;
    Set<Integer> arr = new HashSet<>();

    public EfficiencyHashSet(int numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
    }

    public long timeAddToHashSet() {
        long start = 0;
        start = -System.currentTimeMillis();
        for (int i = 0; i < numberOfIterations; i++) {
            arr.add(i);
        }
        start += System.currentTimeMillis();
        return start;
    }

    public long timeSearchInHashSet(int indexElements) {
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


    public long timeRemoveWithHashSet() {
        long start = 0;
        start = -System.currentTimeMillis();
        for (int i = 0; i < arr.size(); i++) {
            arr.remove(i);
        }
        start += System.currentTimeMillis();
        return start;
    }

    public long timeAddObjectsToHashSet(String text) {
        long start = 0;
        Set<String> arrPopulate = new HashSet<>();
        start = -System.currentTimeMillis();
        for (int i = 0; i < numberOfIterations; i++) {
            arrPopulate.add(text + i);
        }
        start += System.currentTimeMillis();
        return start;
    }
}
