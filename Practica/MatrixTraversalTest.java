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
        int[] expected = MatrixTraversal.print1(arr);
        assertArrayEquals(actual, expected);
    }
    @Test
    public void testPrintOne() throws Exception {
        int[][] arr = {{1}};
        int[] actual = {1};
        assertArrayEquals(actual, MatrixTraversal.print1(arr));
    }

    @Test
    public void testPrintOneOne() throws Exception {
        int[][] arr = {{1}, {2}};
        int[] actual = {1, 2};
        assertArrayEquals(actual, MatrixTraversal.print1(arr));
    }

    @Test
    public void testPrintOneTwo() throws Exception {
        int[][] arr = {{2, 4}};
        int[] actual = {2, 4};
        assertArrayEquals(actual, MatrixTraversal.print1(arr));
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
        assertArrayEquals(actual, MatrixTraversal.print1(arr));
    }

    @Test
    public void testPrintTwoThree() throws Exception {
        int[][] arr = {{1, 4}, {2, 4}, {1, 5}};
        int[] actual = {1, 4, 4, 5, 1, 2};
        assertArrayEquals(actual, MatrixTraversal.print1(arr));
    }

    @Test
    public void testPrintThreeTwo() throws Exception {
        int[][] arr = {{1, 3, 4}, {2, 6, 7}};
        int[] actual = {1, 3, 4, 7, 6, 2};
        assertArrayEquals(actual, MatrixTraversal.print1(arr));
    }

    @Test
    public void testPrintThreeThree() throws Exception {
        int[][] arr = {{1, 3, 4}, {2, 6, 7}, {1, 8, 9}};
        int[] actual = {1, 3, 4, 7, 9, 8, 1, 2, 6};
        assertArrayEquals(actual, MatrixTraversal.print1(arr));
    }

    @Test
    public void testPrintThreeFore() throws Exception {
        int[][] arr = {{1, 3, 4}, {2, 6, 7}, {1, 8, 9}, {2, 6, 7}};
        int[] actual = {1, 3, 4, 7, 9, 7, 6, 2, 1, 2, 6, 8};
        assertArrayEquals(actual, MatrixTraversal.print1(arr));
    }

    @Test
    public void testPrintForeThree() throws Exception {
        int[][] arr = {{1, 3, 4, 7}, {2, 6, 7, 1}, {1, 8, 9, 3}};
        int[] actual = {1, 3, 4, 7, 1, 3, 9, 8, 1, 2, 6, 7};
        assertArrayEquals(actual, MatrixTraversal.print1(arr));
    }

    @Test
    public void testPrintForeFore() throws Exception {
        int[][] arr = {{1, 3, 4, 7}, {2, 6, 7, 1}, {1, 8, 9, 3}, {1, 4, 4, 4}};
        int[] actual = {1, 3, 4, 7, 1, 3, 4, 4, 4, 1, 1, 2, 6, 7, 9, 8};
        assertArrayEquals(actual, MatrixTraversal.print1(arr));
    }
}