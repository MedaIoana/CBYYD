package cbyyd_app.exceptions;

public class ThePatientDoesNotExistsExeption extends Exception {
    private String username;

    public ThePatientDoesNotExistsExeption(String username){
        super(String.format("Patient %s does not exists in the list!",username));
        this.username=username;
    }
    public String getUsername(){ return username; }
}
