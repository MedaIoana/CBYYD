package cbyyd_app.user;

public class User  {
    private String username;
    private String password;
    private String role;
    private String code;

    public User() {
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
   /*@Override
   public boolean equals(Object o) {
       if (this == o) return true;
       if (o == null || getClass() != o.getClass()) return false;

       User user = (User) o;

       if (!username.equals(user.username)) return false;
       if (!password.equals(user.password)) return false;
       return role.equals(user.role);
   }*/

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}



