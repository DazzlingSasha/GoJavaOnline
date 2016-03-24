package Practica;

public class MatrixTraversal {
    public int[] print1(int[][] input) {

        int width = input[0].length;
        int height = input.length;
        int lengthArray = width * height;
        int index = 0;
        int startWidth = 0;
        int startHeight = 0;
        int endWidth = width - 1;
        int endHeight = height - 1;

        int[] arr = new int[lengthArray];

        while (lengthArray > index) {

            if (lengthArray > index) {
                for (int i = startWidth; i <= endWidth; i++) {
                    arr[index++] = input[startHeight][i];

                    if (i == endWidth) {
                        endWidth = startWidth;
                        startWidth = i;
                    }
                }
            }

            if (height > 2 && lengthArray > index) {
                startHeight++;
                for (int i = startHeight; i < endHeight; i++) {
                    arr[index++] = input[i][startWidth];

                    if (i == endHeight - 1) {
                        endHeight = startHeight;
                        startHeight = i;
                    }
                }
            }

            startHeight++;
            if (lengthArray > index) {
                for (int i = startWidth; i >= endWidth; i--) {
                    arr[index++] = input[startHeight][i];

                    if (i == endWidth) {
                        endWidth = startWidth;
                        startWidth = i;
                    }
                }
            }

            if (height > 2 && lengthArray > index) {
                startHeight--;
                for (int i = startHeight; i >= endHeight; i--) {
                    arr[index++] = input[i][startWidth];

                    if (i == endHeight) {
                        endHeight = startHeight;
                        startHeight = i;
                    }
                }
            }
            startWidth++;
            endWidth--;
            height = height - 2;

        }
        return arr;
    }

}