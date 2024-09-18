package sample.Users;

public class User {
    final private String login;
    private String password;

    public User(String Login, String Password) {
        this.login = Login;
        this.password = Password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
