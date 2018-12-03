package by.training.task.entities;

/**
 * Logged user class with properties <b>login</b>, <b>password</b> and
 * <b>role</b>
 * @author Anton Puhachou
 */
public class LoggedUser {

    /**field login*/
    private String login;

    /**field password*/
    private String password;

    /**field role in system*/
    private String role;

    /**
     * Constructor to create a specific object
     * @param login from database
     * @param password from database
     * @param role in system
     */
    public LoggedUser(String login, String password, String role){
        this.login = login;
        this.password = password;
        this.role = role;
    }

    /**
     * get method of {@link LoggedUser#login}
     * @return login from database
     */
    public String getLogin() {
        return login;
    }

    /**
     * set method of {@link LoggedUser#login}
     * @param login entity login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * get method of {@link LoggedUser#password}
     * @return password from database
     */
    public String getPassword() {
        return password;
    }

    /**
     * set method of {@link LoggedUser#password}
     * @param password from database
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * get method of {@link LoggedUser#role}
     * @return role in system
     */
    public String getRole() {
        return role;
    }

    /**
     * set method of {@link LoggedUser#role}
     * @param role in system
     */
    public void setRole(String role) {
        this.role = role;
    }
}
