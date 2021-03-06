package by.training.task.entities;

import org.json.JSONObject;

import java.util.Objects;

/**
 * Administrator class with properties <b>login</b>, <b>password</b> and read
 * only id
 * @author Anton Puhachou
 */
public class Administrator {

    /**field id*/
    private int id;

    /**field login*/
    private String login;

    /**field password*/
    private String password;

    /**
     * Constructor to create a specific object <b>with</b> id
     * @param id entity id
     * @param login administrator login
     * @param password administrator password
     * @see Administrator#Administrator(String, String)
     */
    public Administrator(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    /**
     * Constructor to create a specific object <b>without</b> id
     * @param login administrator login
     * @param password administrator password
     * @see Administrator#Administrator(int, String, String)
     */
    public Administrator(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * get method of {@link Administrator#id}
     * @return id of entity
     */
    public int getId() {
        return id;
    }

    /**
     * get method of {@link Administrator#login}
     * @return login of administrator
     */
    public String getLogin() {
        return login;
    }

    /**
     * set method of {@link Administrator#login}
     * @param login administrator login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * get method of {@link Administrator#password}
     * @return password of administrator
     */
    public String getPassword() {
        return password;
    }

    /**
     * set method of {@link Administrator#password}
     * @param password administrator password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * indicates whether some other object is "equal to" this one
     * @param o object that represents other object
     * @return true if other object equal to this, in other case false
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return login.equals(that.login) &&
                password.equals(that.password);
    }

    /**
     * returns a hash code value for the object
     * @return a hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    /**
     * returns a JSON string that represents object
     *
     * @return JSON string
     */
    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("login", login);
        jsonObject.put("password", password);
        return jsonObject.toString();
    }
}
