package ModuleLesson6.Task1.Directory;

import ModuleLesson8.PrintAndSortCollection;

import java.util.ArrayList;

/**
 * Created by Konfetka on 27.12.2015.
 */
public class TheDirectory {
    public static void main(String[] args){
        File imageFile = new Image();
        File audioFile = new Audio();
        File textFile = new Text();


        ArrayList<File> listFilesInDirectory = new ArrayList<>();
            listFilesInDirectory.add(imageFile);
            listFilesInDirectory.add(audioFile);
            listFilesInDirectory.add(textFile);

        // The homework task 8
        PrintAndSortCollection<File> printAndSortCollection = new PrintAndSortCollection<File>();
        printAndSortCollection.printList(listFilesInDirectory); //до сортировки
        printAndSortCollection.sortList(listFilesInDirectory);  //сортировка
        printAndSortCollection.printList(listFilesInDirectory); //после сортировки
        // The end task 8

        try {

            for(File file : listFilesInDirectory) {
                if (file.getNameFile() == null || ("").equals(file.getNameFile())) {
                    throw new IllegalStateException(file.getNameFile(), file);
                }
                System.out.println(file.getNameFile());
            }

        }catch(IllegalStateException e){
            if(e.getMessage() == null){
                System.out.println("The path is absent in class "+e.getCause().getClass().getSimpleName() + " = " + e.getMessage());
            }else{
                System.out.println("In the class the path empty " + e.getCause().getClass().getSimpleName() + ".");
            }

        }
    }


}
