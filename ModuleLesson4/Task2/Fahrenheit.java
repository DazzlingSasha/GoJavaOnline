package ModuleLesson4.Task2;

/**
 * Created by Dazzling on 21.12.15.
 */
public class Fahrenheit {
    private int temp;

    public Fahrenheit(int temp){
        this.temp = temp;
    }
    public double ConversionFromCelsiusToFahrenheit(){
        return (temp -32)/1.8d;
    }
}
