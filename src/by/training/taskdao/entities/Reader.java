package by.training.taskdao.entities;

/**
 * Reader class with properties <b>login</b>, <b>password</b>,
 * <b>language</b> locale and read only id
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
     * Constructor to create a specific object <b>without</b> id
     * @param login reader login
     * @param password reader password
     * @param languageId bound language id
     * @see Reader#Reader(int, String, String, int)
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
     * @see Reader#Reader(String, String, int)
     */
    public Reader(int id, String login, String password, int languageId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.languageId = languageId;
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
}
