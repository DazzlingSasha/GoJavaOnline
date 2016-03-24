package Practica;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class MatrixTraversalTest {
    static MatrixTraversal matrix;

    @BeforeClass
    public static void setUp() throws Exception {
        matrix = new MatrixTraversal();
    }
    @Test
    public void testPrintZero() throws Exception {
        int[][] arr = {{}};
        int[] actual = {};
        int[] expected = matrix.print1(arr);
        assertArrayEquals(actual, expected);
    }
    @Test
    public void testPrintOne() throws Exception {
        int[][] arr = {{1}};
        int[] actual = {1};
        assertArrayEquals(actual, matrix.print1(arr));
    }

    @Test
    public void testPrintOneOne() throws Exception {
        int[][] arr = {{1}, {2}};
        int[] actual = {1, 2};
        int[] expected = matrix.print1(arr);

        assertArrayEquals(actual, expected);
    }

    @Test
    public void testPrintOneTwo() throws Exception {
        int[][] arr = {{2, 4}};
        int[] actual = {2, 4};
        assertArrayEquals(actual, matrix.print1(arr));
    }

    //    @Test
//    public void testPrintTwoOne() throws Exception {
//        int[][] arr = {{1},{2}};
//        int[] actual = {1, 2};
//        assertArrayEquals(actual, matrix.print(arr));
//    }
    @Test
    public void testPrintTwoTwo() throws Exception {
        int[][] arr = {{1, 5}, {2, 5}};
        int[] actual = {1, 5, 5, 2};
        assertArrayEquals(actual, matrix.print1(arr));
    }

    @Test
    public void testPrintTwoThree() throws Exception {
        int[][] arr = {{1, 4}, {2, 4}, {1, 5}};
        int[] actual = {1, 4, 4, 5, 1, 2};
        assertArrayEquals(actual, matrix.print1(arr));
    }

    @Test
    public void testPrintThreeTwo() throws Exception {
        int[][] arr = {{1, 3, 4}, {2, 6, 7}};
        int[] actual = {1, 3, 4, 7, 6, 2};
        assertArrayEquals(actual, matrix.print1(arr));
    }

    @Test
    public void testPrintThreeThree() throws Exception {
        int[][] arr = {{1, 3, 4}, {2, 6, 7}, {1, 8, 9}};
        int[] actual = {1, 3, 4, 7, 9, 8, 1, 2, 6};
        assertArrayEquals(actual, matrix.print1(arr));
    }

    @Test
    public void testPrintThreeFore() throws Exception {
        int[][] arr = {{1, 3, 4}, {2, 6, 7}, {1, 8, 9}, {2, 6, 7}};
        int[] actual = {1, 3, 4, 7, 9, 7, 6, 2, 1, 2, 6, 8};
        assertArrayEquals(actual, matrix.print1(arr));
    }

    @Test
    public void testPrintForeThree() throws Exception {
        int[][] arr = {{1, 3, 4, 7}, {2, 6, 7, 1}, {1, 8, 9, 3}};
        int[] actual = {1, 3, 4, 7, 1, 3, 9, 8, 1, 2, 6, 7};
        assertArrayEquals(actual, matrix.print1(arr));
    }

    @Test
    public void testPrintForeFore() throws Exception {
        int[][] arr = {{1, 3, 4, 7}, {2, 6, 7, 1}, {1, 8, 9, 3}, {1, 4, 6, 4}};
        int[] actual = {1, 3, 4, 7, 1, 3, 4, 6, 4, 1, 1, 2, 6, 7, 9, 8};
        assertArrayEquals(actual, matrix.print1(arr));
    }
    @Test
    public void testPrintFiveFive() throws Exception {
        int[][] arr = {{1, 3, 4, 7, 8}, {2, 6, 7, 1, 9}, {1, 8, 9, 3, 8}, {1, 4, 6, 4, 6}, {1, 8, 9, 3, 8}};
        int[] actual = {1, 3, 4, 7, 8, 9, 8, 6, 8, 3, 9, 8, 1, 1, 1, 2, 6, 7, 1, 3, 4, 6, 4, 8, 9};
        assertArrayEquals(actual, matrix.print1(arr));
    }
}