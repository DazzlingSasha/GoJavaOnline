package Task2;

/**
 * Created by Konfetka on 27.12.2015.
 */
public class UserNameException extends Throwable {
    private String userName;
    public UserNameException(final String userName) {
        this.userName = userName;
    }
    public String getUserName(){
        return userName;
    }
}
