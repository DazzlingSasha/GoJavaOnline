package Practica;

public class MatrixTraversal {
    //    public static int[] print(int[][] input) {
//        int width = input[0].length;
//        int height = input.length;
//
//        int[] arr = new int[width * height];
//
//        int index = 0;
//        int start = 0;
//        int startH = 0;
//
//
//        while (index != width * height) {
//            if (width == 1 && height == 1) {
//                arr[index++] = input[start][startH];
//            } else if ((width - start - 1) == 1 && (height - startH - 1) == 1 && width > 2 && height > 2) {
//                arr[index++] = input[start][startH];
//            } else if (width - start - 1 == 1 && height - startH - 1 == 2) {
//                arr[index++] = input[start][start];
//                arr[index++] = input[start + 1][start];
//            } else if (width - start - 1 == 2 && height - startH - 1 == 1) {
//                arr[index++] = input[start][start];
//                arr[index++] = input[start][start + 1];
//            } else {
//                for (int i = start; i < width - startH - 1; i++) { //for (int i = 0; i < width - 1; i++) i=1 width - 2
//                    arr[index++] = input[start][i];
//                }
//                for (int i = start; i < height - startH - 1; i++) {//for (int j = 0; j < height - 1; j++)  i=1 height - 2
//                    arr[index++] = input[i][width - startH - 1];
//                }
//
//                for (int i = width - startH - 1; i > start; i--) {//for (int i = width - 1; i > 0; i--)  i = width - 2 > 1
//                    arr[index++] = input[height - startH - 1][i];
//                }
//                for (int i = height - startH - 1; i > start; i--) {//for (int i = height - 1; i > 0; i--)  i= height -2 > 1
//                    arr[index++] = input[i][start];
//                }
//            }
//            start++;
//            startH++;
//        }
//
//        return arr;
//    }

    static int index = 0;
    static int startW = 0;
    static int startH = 0;
    static int width1 = 0;
    static int value = 1;
    static int start = 0;
    static int end = 0;

    public static int[] print1(int[][] input) {
        int width = input[0].length;
        int height = input.length;

        int[] arr = new int[width * height];


//        System.out.println("width: " + width + ". height: " + height);
        for (int i = 0; i < width * height; i++) {

            switch (value) {
                case 1:
                    if (i < width) {
                        arr[i] = input[startH][startW++];
                    } else {
                        if (height > 2) {
                            value = 2;
                            startW--;
                        } else {
                            value = 3;
                            startH++;
                            startW--;
                        }
                    }
                    break;
                case 2:
                    startH++;
                    if (startH < height) {
                        arr[i] = input[startH][startW];
                    } else {
                        value = 3;
                    }
                    break;
                case 3:
                    if (startW >= 0 ) {
                        arr[i] = input[startH][startW--];
                    } else {
                        value = 4;
                    }
                    break;
                case 4:
                    if (startH >= 1) {
                        arr[i] = input[startH--][startW];
                    }
                    break;
            }






        }
//        if (height == 1 && width == 0) {
//            System.out.println("null");
//        }
//        if (height == 1 && width == 1) {
//            arr[index++] = input[startH][startW];
//            System.out.println(arr[index - 1]);
//        }
//        if (height == 1 && width == 2) {
//            for (int i = startW; i < input[startH].length; i++) {
//                arr[index++] = input[startH][i];
//            }
//        }
//        if (height == 2 && width == 1) {
//            for (int i = startH; i < input.length; i++) {
//                arr[index++] = input[i][startW];
//            }
//        }
//        if (height == 2 && width >= 2) {
//            for (int i = startW; i < input[startH].length; i++) {
//                arr[index++] = input[startH][i];
//            }
//            startH++;
//            for (int k = input[startH].length-1; k >= startW; k--) {
//                arr[index++] = input[startH][k];
//            }
//        }
//
//        if (height >= 3 && width == 2) {
//            for (int i = startW; i < input[startH].length; i++) {
//                arr[index++] = input[startH][i];
//            }
//            startH++;
//            for (int k = input[startH].length-1; k >= startW; k--) {
//                arr[index++] = input[startH][k];
//            }
//        }

        return arr;
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 5}, {2, 5}};
//        for (int d : arr[0]){
//            System.out.println(arr.length + " d" + arr[0].length+" --->"+ d);
//        }
//        for (int d : arr[1]){
//            System.out.println(arr.length + " d" + arr[0].length+" --->"+ d);
//        }
//        System.out.println(arr[0][0]);
        System.out.println(print1(arr));

    }
}