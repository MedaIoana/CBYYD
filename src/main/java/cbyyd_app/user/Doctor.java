package cbyyd_app.user;

public class Doctor extends User {
    private String code;

    public Doctor(String var1, String var2, String var3) {
        super(var1, var2);
        this.code = var3;
    }

    public String getCode() {
        return this.code;
    }
}
