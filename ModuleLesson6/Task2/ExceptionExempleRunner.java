package Task2;

import java.util.Scanner;

public class ExceptionExempleRunner {
    public static void main(String[] args){

        final Scanner scaner = new Scanner(System.in);


        final String userName;
        final String userAge;

        try {
            System.out.println("Hello! Pleas enter the Name with only letters:");
            userName = scaner.next();
            char[] charsArray = userName.toCharArray();
            for(int indexChar : charsArray){
                if ((65 > indexChar || indexChar > 90) && (97 > indexChar || indexChar > 122)) {
                    throw new UserNameException(userName);
                }
            }
            System.out.println("Your Name is: " + userName);

            System.out.println(" Please enter your age: ");
            userAge = scaner.next();
            int age = Integer.parseInt(userAge);
            if(age < 0){
                throw new NegativeAgeException(age);
            }
            System.out.println("Your age is: " + age);

        }catch (UserNameException e) {
            System.out.println("Error: The name you entered does not consist of letters." +
                    "You entered: " + e.getUserName());
        }catch(NumberFormatException e){
            System.out.println("Error: Age should be an integer ");
        } catch (NegativeAgeException e) {
            System.out.println("Error: Enter age is: '"+e.getAgeValue()+"'. Age should be >= 0");
        }


    }
}
