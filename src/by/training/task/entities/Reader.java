package by.training.task.entities;

import org.json.JSONObject;

import java.util.Objects;

/**
 * Reader class with properties <b>login</b>, <b>password</b>,
 * <b>language</b> config and read only id
 * @author Anton Puhachou
 */
public class Reader {

    /**field id*/
    private int id;

    /**field login*/
    private String login;

    /**field password*/
    private String password;

    /**field language id*/
    private int languageId;

    /**
     * field bound language
     */
    private Language language;

    /**
     * Constructor to create a specific object <b>without</b> id
     *
     * @param login      reader login
     * @param password   reader password
     * @param languageId bound language id
     */
    public Reader(String login, String password, int languageId) {
        this.login = login;
        this.password = password;
        this.languageId = languageId;
    }

    /**
     * Constructor to create a specific object <b>with</b> id
     * @param id entity id
     * @param login reader login
     * @param password reader password
     * @param languageId bound language id
     */
    public Reader(int id, String login, String password, int languageId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.languageId = languageId;
    }

    /**
     * Constructor to create a specific object <b>with</b> id
     * @param id entity id
     * @param login reader login
     * @param password reader password
     * @param language bound language object
     */
    public Reader(int id, String login, String password, Language language) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.language = language;
        this.languageId = language.getId();
    }

    /**
     * get method of {@link Reader#id}
     * @return id of entity
     */
    public int getId() {
        return id;
    }

    /**
     * get method of {@link Reader#login}
     * @return reader login
     */
    public String getLogin() {
        return login;
    }

    /**
     * set method of {@link Reader#login}
     * @param login reader login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * get method of {@link Reader#password}
     * @return reader password
     */
    public String getPassword() {
        return password;
    }

    /**
     * set method of {@link Reader#password}
     * @param password reader password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * get method of {@link Reader#languageId}
     * @return bound language id
     */
    public int getLanguageId() {
        return languageId;
    }

    /**
     * set method of {@link Reader#languageId}
     * @param languageId bound language id
     */
    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    /**
     * get method of {@link Reader#language}
     * @return bound language object
     * */
    public Language getLanguage() {
        return language;
    }

    /**
     * set method of {@link Reader#language} with setting {@link Reader#languageId}
     * @param language bound language object
     * */
    public void setLanguage(Language language) {
        this.language = language;
        this.languageId = language.getId();
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
        Reader reader = (Reader) o;
        return login.equals(reader.login) &&
                password.equals(reader.password);
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
     * @return JSON string
     * */
    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("login", login);
        jsonObject.put("password", password);
        if (language != null) {
            jsonObject.put("language", language.toString());
        }
        return jsonObject.toString();
    }
}
