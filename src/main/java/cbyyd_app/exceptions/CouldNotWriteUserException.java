package cbyyd_app.exceptions;

public class CouldNotWriteUserException extends Exception
{
    public CouldNotWriteUserException()
    {
        super("Could not write users");
    }
}
