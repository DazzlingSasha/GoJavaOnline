package Practica;

import ModuleLesson5.ShellSort;

import java.util.*;

/**
 * Знайти площу яку займають N прямокутників на площині. У всіх прямокутників одна зі сторін лежить на осі абсцис (X).
 * Дано три масива довжиною N. В першому масиві Х координата нижньої-лівої вершини і-го прямокутника , висота в другому,
 * ширина в третьому. Всі значення невід'ємні.
 * Приклад: X - [0, 0] H - [20, 10] W - [10, 20] Відповідь - 300.
 */
public class RectangleSquare {
    public int measure(int[] x, int[] h, int[] w) {

        boolean isInterrupt = true;
        int searchStep = (x.length % 2 == 0) ? x.length / 2 : (x.length / 2)-1;
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
                }else{
                    count++;
                }
            }
            if(count == x.length - 1){
                isInterrupt = false;
            }
            count = 0;
        }
        int sum = 0;
        int minX = x[0];
        int maxH = h[0];
        int maxW = w[0]-x[0];
        for(int i = 0; i < x.length; i++){
            if(maxW < (w[i]-x[i]) && minX>=x[i] && maxH<h[i]){ // max block
                sum = h[i] * w[i];
            }
//            if(maxH<h[i] && maxW<(w[i]-x[i]) && 0 <= x[i] && x[i]<maxW){
//
//            }
//            if(i==0){
//               sum += h[i] * w[i];
//            }


        }



        for(int c : x){
            System.out.print(c+" ");
        }
        System.out.println();
        for(int c : h){
            System.out.print(c+" ");
        }
        System.out.println();
        for(int c : w){
            System.out.print(c+" ");
        }
        System.out.println();
        return sum;
    }

    private void swap(int[] x, int searchStep, int i) {
        int minValue;
        minValue = x[i];
        x[i] = x[i + searchStep];
        x[i + searchStep] = minValue;
    }

    public static void main(String[] args) {
        RectangleSquare a = new RectangleSquare();
        int[] x = {60, 0, 20, 70, 20, 30};
        int[] h = {10,20,30, 40,50,90};
        int[] w = {10,10,20,10,80,90};
        System.out.println(a.measure(x, h, w));
//        ShellSort s = new ShellSort();
//        s.sort(x);
//        for(int h1 : x) {
//            System.out.print(h1+" ");
//        }
    }
}
