package Task2;

/**
 * Created by Konfetka on 27.12.2015.
 */
public class NegativeAgeException extends Throwable {
    private int ageValue;

    public NegativeAgeException(final int ageValue) {
        this.ageValue = ageValue;
    }

    public int getAgeValue() {
        return ageValue;
    }


}
