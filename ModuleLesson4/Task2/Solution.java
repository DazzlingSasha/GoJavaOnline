package ModuleLesson4.Task2;

/**
 * Created by Dazzling on 21.12.15.
 */
public class Solution {
    public static void main(String[] args){
        Celsius celsius = new Celsius(30);
        System.out.println(celsius.ConversionFromFahrenheitToCelsius());
        Fahrenheit fahrenheit = new Fahrenheit(86);
        System.out.println(fahrenheit.ConversionFromCelsiusToFahrenheit());
    }
}
