package ModuleLesson10;


import ModuleLesson9.EncryptAndDecryptTheText;

import java.io.*;
import java.util.Scanner;

public class SaveAndEncryptOrLoadAndDecryptFile {
    public void saveEncryptFile(String file) {
        EncryptAndDecryptTheText encryptDecrypt = new EncryptAndDecryptTheText();
        File pathOnFile = new File(file);
        try(final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            final Scanner scanner = new Scanner(System.in)) {
            System.out.println("The enter text for encryption. For exit and save in the file, enter: \'exit\'. The file is saved in the directory: "+pathOnFile.getAbsolutePath());
            String text = scanner.nextLine();

            do {
                out.write(encryptDecrypt.encrypt(text+'\n'));
                text = scanner.nextLine();

            } while (!text.equals("exit"));

        } catch (FileNotFoundException e) {
            System.out.println("Do not enter a file name or path");
                    e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error Input/Output ");
            e.printStackTrace();
        }
    }
    public void loadDecryptFile(String file) {
        EncryptAndDecryptTheText encryptDecrypt = new EncryptAndDecryptTheText();

        try(final BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {

            while (in.ready()) {
                System.out.println(encryptDecrypt.decrypt(in.readLine()));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Do not enter a file name or path");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error Input/Output ");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        final String file = "c:\\111.txt";
        SaveAndEncryptOrLoadAndDecryptFile s = new SaveAndEncryptOrLoadAndDecryptFile();
        s.saveEncryptFile(file);
        s.loadDecryptFile(file);

    }
}
