package cbyyd_app.exceptions;

public class CodeAlreadyExist extends Throwable {
    private String code;
    public CodeAlreadyExist(String code)
    {
        super(String.format("An account with this code %s already exists!", code));
        this.code=code;
    }
    public String getCode()
    {
        return code;
    }
}
