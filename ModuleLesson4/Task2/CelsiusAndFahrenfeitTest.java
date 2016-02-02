package ModuleLesson4.Task2;

import org.junit.Test;

import static org.junit.Assert.*;

public class CelsiusAndFahrenfeitTest {

    @Test
    public void conversionFromFahrenheitToCelsiusTest(){
        Celsius celsius = new Celsius(30);
        int result = (int) celsius.ConversionFromFahrenheitToCelsius();
        assertEquals(86, result);
    }
    @Test
    public void conversionFromCelsiusToFahrenheitTest(){
        Fahrenheit fahrenheit = new Fahrenheit(86);
        int result = (int) fahrenheit.ConversionFromCelsiusToFahrenheit();
        assertEquals(30, result);
    }
}