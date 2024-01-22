package mod;

public class CreateUser {
    public CreateUser(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    private final String email;
    private final String password;
    private final String name;

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}