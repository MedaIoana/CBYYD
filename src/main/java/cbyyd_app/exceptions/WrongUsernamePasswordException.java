package cbyyd_app.exceptions;

public class WrongUsernamePasswordException extends Throwable {
    public WrongUsernamePasswordException()
    {
        super("The username or password is incorrect");
    }
}
