package ModuleLesson6.Task2;

public class UserNameException extends Throwable {
    private String userName;
    public UserNameException(final String userName) {
        this.userName = userName;
    }
    public String getUserName(){
        return userName;
    }
}
