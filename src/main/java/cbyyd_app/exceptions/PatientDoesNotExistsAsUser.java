package cbyyd_app.exceptions;

public class PatientDoesNotExistsAsUser extends Exception {
    private String username;

    public PatientDoesNotExistsAsUser(String username){
        super(String.format("Patient %s does not exists as a user!",username));
        this.username=username;
    }
    public String getUsername(){ return username; }
}
