package ModuleLesson5.Additionally;


public class Calculeter {
//
    public static int kayMatrix(int[][] result){
        int kayMatrix = result[0][0] * result[1][1] - result[1][0] * result[0][1];
        System.out.println("Kay matrix = " + kayMatrix);
        return kayMatrix;
    }

    public int[][] addMatrix(int[][] matrix1, int[][] matrix2){
        int[][] result = new int[2][2];
        if(matrix1.length != 2 || matrix2.length != 2 || matrix1[0].length != 2 || matrix1[1].length != 2 || matrix2[0].length != 2 || matrix2[1].length != 2){
            System.out.println("One or second matrix is not correct");
        }else {

            result[0][0] = matrix1[0][0] + matrix2[0][0];
            result[0][1] = matrix1[0][1] + matrix2[0][1];
            result[1][0] = matrix1[1][0] + matrix2[1][0];
            result[1][1] = matrix1[1][1] + matrix2[1][1];
        }
        return result;
    }

    public int[][] differenceMatrix(int[][] matrix1, int[][] matrix2){
        int[][] result = new int[2][2];
        if(matrix1.length != 2 || matrix2.length != 2 || matrix1[0].length != 2 || matrix1[1].length != 2 || matrix2[0].length != 2 || matrix2[1].length != 2){
            System.out.println("One or second matrix is not correct");
        }else {
            result[0][0] = matrix1[0][0] - matrix2[0][0];
            result[0][1] = matrix1[0][1] - matrix2[0][1];
            result[1][0] = matrix1[1][0] - matrix2[1][0];
            result[1][1] = matrix1[1][1] - matrix2[1][1];

            System.out.println("Result: Difference two matrices ");
            System.out.println("|" + result[0][0] + "," + result[0][1] + "|");
            System.out.println("|" + result[1][0] + "," + result[1][1] + "|");
        }
        return result;
    }
    public int[][] multiplicationMatrix(int[][] matrix1, int[][] matrix2){
        int[][] result = new int[2][2];
        if(matrix1.length != 2 || matrix2.length != 2 || matrix1[0].length != 2 || matrix1[1].length != 2 || matrix2[0].length != 2 || matrix2[1].length != 2){
            System.out.println("One or second matrix is not correct");
        }else {
            result[0][0] = matrix1[0][0] * matrix2[0][0] + matrix1[0][1] * matrix2[1][0];
            result[0][1] = matrix1[0][0] * matrix2[0][1] + matrix1[0][1] * matrix2[1][1];
            result[1][0] = matrix1[1][0] * matrix2[0][0] + matrix1[1][1] * matrix2[1][0];
            result[1][1] = matrix1[1][0] * matrix2[0][1] + matrix1[1][1] * matrix2[1][1];

            System.out.println("Result: Multiplication two matrix");
            System.out.println("|" + result[0][0] + "," + result[0][1] + "|");
            System.out.println("|" + result[1][0] + "," + result[1][1] + "|");
        }
        return result;
    }
    public static void main(String[] args) {
        int[][] matrix1 = new int[][]{{1,2}, {3, 4}};
        int[][] matrix2 = new int[][]{{5,0}, {7, 8}};
        Calculeter calculeter = new Calculeter();

        int a = 52;
        if (a != Calculeter.kayMatrix(calculeter.addMatrix(matrix1, matrix2))) throw new AssertionError();
    }
}
