package cbyyd_app.exceptions;

public class CouldNotWriteUserException extends RuntimeException{
    public CouldNotWriteUserException()
    {
        super("Could not write users");
    }
}
