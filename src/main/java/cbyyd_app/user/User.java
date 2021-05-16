package cbyyd_app.user;

import java.util.ArrayList;
import java.util.List;

public class User  {
    private List<String> patients=new ArrayList<>();
    private List<String> treatments=new ArrayList<>();
    private List<Week> schedule=new ArrayList<>();
    private String username;
    private String password;
    private String role;
    private String code;

    public User(){
    }

    public User(String username) {
        this.username=username;
    }

    public User(String username, String password,String role,String code) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.code = code;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code;}


    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof User)) {
            return false;
        } else {
            User user = (User)o;
            boolean u = false;
            boolean p = false;
            if (this.password.equals(user.password)) {
                u = true;
            }

            if (this.username.equals(user.username)) {
                p = true;
            }

            return u && p;
        }
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }

    public List<String> getPatients() {
        return patients;
    }

    public void setPatients(List<String> patients) {
        this.patients = patients;
    }

    public List<String> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<String> treatments) {
        this.treatments = treatments;
    }

    public List<Week> getSchedule() { return schedule; }

    public void setSchedule(List<Week> schedule) { this.schedule = schedule; }

}



