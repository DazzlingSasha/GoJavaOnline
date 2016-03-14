package CoursesEE.ModuleNumberOne;

import java.io.*;
import java.util.Scanner;

public class CollectionEfficiency {
    public static void main(String[] args) {
        int[] arrayStepsInList = {1_000, 10_000, 1000_000};
//        int step = 10000;
        int cycle = 10;


        final BufferedWriter out;
        try (final Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter path! Where will lie file. Example \"c:\\1.txt\"");
            String text = scanner.nextLine();

            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(text)));

            for (int step : arrayStepsInList) {

//        System.out.println("_________________________ArrayList________________________________");

                long timeAddArrayList = 0;
                long timeGetArrayList = 0;
                long timeSearchArrayList = 0;
                long timeAddIteratorArrayList = 0;
                long timeRemoveWithIteratorArrayList = 0;
                long timeRemoveWithArrayList = 0;
                long timeAddObjectsArrayList = 0;

                EfficiencyArrayList effArrayList = new EfficiencyArrayList(step);

                for (int i = 0; i < cycle; i++) {

                    timeAddArrayList += effArrayList.timeAddToArrayList();
                    timeGetArrayList += effArrayList.timeGetWithArrayList();
                    timeSearchArrayList += effArrayList.timeSearchInArrayList(100);
                    timeAddIteratorArrayList += effArrayList.timeAddIteratorToArrayList();
                    timeRemoveWithIteratorArrayList += effArrayList.timeRemoveWithArrayListIterator();
                    timeRemoveWithArrayList += effArrayList.timeRemoveWithArrayList();
                    timeAddObjectsArrayList += effArrayList.timeAddObjectsToArrayList("Populate index in Array");
                }

//        System.out.println("_________________________LinkedList________________________________");


                long timeAddLinkedList = 0;
                long timeGetLinkedList = 0;
                long timeSearchLinkedList = 0;
                long timeAddIteratorLinkedList = 0;
                long timeRemoveWithIteratorLinkedList = 0;
                long timeRemoveWithLinkedList = 0;
                long timeAddObjectsLinkedList = 0;

                EfficiencyLinkedList effLinkedList = new EfficiencyLinkedList(step);

                for (int i = 0; i < cycle; i++) {
                    timeAddLinkedList += effLinkedList.timeAddToLinkedList();
                    timeGetLinkedList += effLinkedList.timeGetWithLinkedList();
                    timeSearchLinkedList += effLinkedList.timeSearchInLinkedList(100);
                    timeAddIteratorLinkedList += effLinkedList.timeAddIteratorToLinkedList();
                    timeRemoveWithIteratorLinkedList += effLinkedList.timeRemoveWithLinkedListIterator();
                    timeRemoveWithLinkedList += effLinkedList.timeRemoveWithLinkedList();
                    timeAddObjectsLinkedList += effLinkedList.timeAddObjectsToLinkedList("Populate index in Array");
                }


//        System.out.println("_________________________HashSet________________________________");

                long timeAddHashSet = 0;
                long timeSearchHashSet = 0;
                long timeRemoveWithArrayListHashSet = 0;
                long timeAddObjectsHashSet = 0;

                EfficiencyHashSet effHashSet = new EfficiencyHashSet(step);

                for (int i = 0; i < cycle; i++) {
                    timeAddHashSet += effHashSet.timeAddToHashSet();
                    timeSearchHashSet += effHashSet.timeSearchInHashSet(100);
                    timeRemoveWithArrayListHashSet += effHashSet.timeRemoveWithHashSet();
                    timeAddObjectsHashSet += effHashSet.timeAddObjectsToHashSet("Populate index in Array");
                }


//        System.out.println("_________________________TreeSet________________________________");

                long timeAddTreeSet = 0;
                long timeSearchTreeSet = 0;
                long timeRemoveWithArrayListTreeSet = 0;
                long timeAddObjectsTreeSet = 0;

                EfficiencyTreeSet effTreeSet = new EfficiencyTreeSet(step);

                for (int i = 0; i < cycle; i++) {

                    timeAddTreeSet += effTreeSet.timeAddToTreeSet();
                    timeSearchTreeSet += effTreeSet.timeSearchInTreeSet(100);
                    timeRemoveWithArrayListTreeSet += effTreeSet.timeRemoveWithTreeSet();
                    timeAddObjectsTreeSet += effTreeSet.timeAddObjectsToTreeSet("Populate index in Array");
                }

                String width0 = "Volume elements: " + step;
                String width1 = String.format("|\t%-10s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\r\n",
                        " ", "add", "get", "remove", "contains", "populate", "iterator.add", "iterator.remove");
                String width2 = String.format("|%23s|%31s|%31s|%31s|%31s|\t%31s\t|%31s|%31s|\r\n",
                        " ", " ", " ", " ", " ", " ", " ", " ").replace(" ", "_");
                String width3 = String.format("|\t%-10s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\r\n",
                        "ArrayList", timeAddArrayList, timeGetArrayList, timeRemoveWithArrayList, timeSearchArrayList, timeAddObjectsArrayList, timeAddIteratorArrayList, timeRemoveWithIteratorArrayList);
                String width4 = String.format("|\t%-10s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\r\n",
                        "LinkedList", timeAddLinkedList, timeGetLinkedList, timeRemoveWithLinkedList, timeSearchLinkedList, timeAddObjectsLinkedList, timeAddIteratorLinkedList, timeRemoveWithIteratorLinkedList);
                String width5 = String.format("|\t%-10s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\r\n",
                        "HashSet", timeAddHashSet, "-", timeRemoveWithArrayListHashSet, timeSearchHashSet, timeAddObjectsHashSet, "-", "-");
                String width6 = String.format("|\t%-10s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\t%-18s\t|\r\n",
                        "TreeSet", timeAddTreeSet, "-", timeRemoveWithArrayListTreeSet, timeSearchTreeSet, timeAddObjectsTreeSet, "-", "-");

                out.write("\r\n" + width0 + "\r\n");
                out.write(width1);
                out.write(width2);
                out.write(width3);
                out.write(width4);
                out.write(width5);
                out.write(width6);


            }
            System.out.println("File write on disk");
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Do not enter a file name or path");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error Input/Output ");
            e.printStackTrace();
        }


    }


}
