package ForWork;

/**
 * Created by Dazzling on 16.03.16.
 */
public class FindMaxNumberOfUnits {
    public int inThisArray(int[] input) {
        int count = 0;
        int maxCount = 0;
        int maxNumber = input[0];
        for (int i = 0; i < input.length; i++) {
            for (int k = 0; k < 32; k++) {
                int temp = 1 << k;
                int mask = input[i] & temp;
                if (temp == mask) {
                    count++;
                }
            }
            if (maxCount < count) {
                maxCount = count;
                maxNumber = input[i];
            }
            count = 0;
        }
        return maxNumber;
    }
    public int fromZeroToTheNumberOf(int input) {
        int count = 0;
        int maxCount = 0;
        int maxNumber = 0;
        for (int step = 1; step < input; step++) {
            for (int iterator = 0; iterator < 32; iterator++) {
                int temp = 1 << iterator;
                int mask = step & temp;
                if (temp == mask) {
                    count++;
                }
            }
            if (maxCount < count) {
                maxCount = count;
                maxNumber = step;
            }
            count = 0;
        }
        return maxNumber;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6};
        FindMaxNumberOfUnits max = new FindMaxNumberOfUnits();
        System.out.println(max.inThisArray(a));
        System.out.println(max.fromZeroToTheNumberOf(9));
    }
}
