package LifoFifoStructure;

import java.util.ArrayList;
import java.util.LinkedList;

public class QueueLastInFirstOut {
    private LinkedList<String> listQueue = new LinkedList<>();

    public static void main(String[] args) {
        LinkedList<String> listQueue = new LinkedList<>();
        listQueue.add(0, "sssss");
        listQueue.add(0, "ddd");
        listQueue.add(0, "sssssd1");

        for(String h : listQueue){
            System.out.println(h);
        }

        ArrayList<String> arrQueue = new ArrayList<>();
        arrQueue.add(0, "sssss");
        arrQueue.add(0, "ddd");
        arrQueue.add(0, "sssssd1");

        for(String h : arrQueue){
            System.out.println(h);
        }
        QueueLastInFirstOut s = new QueueLastInFirstOut();

        String[] arr = new String[4];
//        arr[0] = "ddd";
//        arr[1] = "ddd1";
//        arr[2] = "ddd2";
        s.addInArray(arr, "dddddd1");
        s.addInArray(arr, "dddddd2");
        s.addInArray(arr, "dddddd3");

        for(String h : arr){
            System.out.println(h);
        }

    }
    public String[] addInArray(String[] arr, String text){

        for(int i=0; i<arr.length; i++){
            if((null)==(arr[i])){
                arr[i] = text;
                break;
            }
        }
        return arr;
    }
    public String fifo(String[] arr){
        return arr[0];
    }
    public void lifo(){}
}
