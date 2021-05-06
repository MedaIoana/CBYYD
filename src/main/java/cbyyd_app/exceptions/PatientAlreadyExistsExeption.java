package cbyyd_app.exceptions;

public class PatientAlreadyExistsExeption extends Exception {
    private String username;

    public PatientAlreadyExistsExeption(String username){
        super(String.format("Patient %s is already in the list!",username));
        this.username=username;
    }
    public String getUsername(){ return username; }
}
