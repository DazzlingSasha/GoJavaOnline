package Practica;

import java.util.HashMap;
import java.util.Map;

public class RectangleSquare {
    public int measure(int[] x, int[] h, int[] w) {
        boolean isInterrupt = true;
        int searchStep = (x.length % 2 == 0) ? x.length / 2 : (x.length / 2) - 1;
        int count = 0;

        while (isInterrupt) {
            for (int i = 0; i < searchStep; i++) {

                if (x[i] > x[i + searchStep]) {
                    swap(x, searchStep, i);
                    swap(h, searchStep, i);
                    swap(w, searchStep, i);
                }
            }

            for (int i = 0; i < x.length - 1; i++) {
                if (x[i] > x[i + 1]) {
                    swap(x, 1, i);
                    swap(h, 1, i);
                    swap(w, 1, i);
                } else {
                    count++;
                }
            }

            if (count == x.length - 1) {
                isInterrupt = false;
            }
            count = 0;
        }

        int sum = 0;
        int maxX = 0;
        int maxH = 0;
        int maxW = 0;

        Map<Integer, Integer> nextHeight = new HashMap<>();

        for (int i = 0; i < x.length; i++) {
            if (nextHeight.containsKey(x[i])) {
                maxH = nextHeight.get(x[i]);
            } else {

                for (Map.Entry<Integer, Integer> searchHeight : nextHeight.entrySet()) {
                    int oX = searchHeight.getKey();
                    if (x[i] <= oX) {
                       maxH = searchHeight.getValue();
                       break;
                    }
                }
            }
            if (maxX <= x[i]) { //1
                sum += h[i] * w[i];
                addHeight(x[i], h[i], w[i], nextHeight);
            } else if (maxX < x[i] + w[i] && maxH >= h[i]) { //2
                sum += (w[i] + x[i] - maxX) * h[i];
                addHeight(x[i], h[i], w[i], nextHeight);

            } else if (maxX <= x[i] + w[i] && maxH < h[i]) {
                sum += (w[i] + x[i] - maxX) * h[i];
                sum += (maxX - x[i]) * (h[i] - maxH);
                addHeight(x[i], h[i], w[i], nextHeight);

            } else if (maxX > x[i] + w[i] && maxH < h[i]) {
                sum += (w[i]) * (h[i] - maxH);
                addHeight(x[i], h[i], w[i], nextHeight);

            }

            if (maxH < h[i]) {
                maxH = h[i];
            }
            if (maxW < w[i] - x[i]) {
                 maxW = w[i] - x[i];

            }
            if (maxX < x[i] + w[i]) {
                maxX = x[i] + w[i];
            }

        }
        return sum;
    }

    private void addHeight(int key, int value, int key1, Map<Integer, Integer> nextHeight) {
        key1 += key;
        nextHeight.put(key, value);
        nextHeight.put(key1, value);
    }

    private void swap(int[] x, int searchStep, int i) {
        int minValue;
        minValue = x[i];
        x[i] = x[i + searchStep];
        x[i + searchStep] = minValue;
    }

}


