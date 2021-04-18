package User;

public class User  {
    private String username;
    private String password;

    public User(String var1, String var2) {
        this.username = var1;
        this.password = var2;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean equals(Object var1) {
        if (var1 == this) {
            return true;
        } else if (!(var1 instanceof User)) {
            return false;
        } else {
            User var2 = (User)var1;
            boolean var3 = false;
            boolean var4 = false;
            if (this.password.equals(var2.password)) {
                var3 = true;
            }

            if (this.username.equals(var2.username)) {
                var4 = true;
            }

            return var3 && var4;
        }
    }
}



