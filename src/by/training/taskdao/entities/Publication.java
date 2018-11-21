package by.training.taskdao.entities;

/**
 * Publication class with properties <b>author</b>, <b>name</b>,
 * <b>language</b> config and read only id
 * @author Anton Puhachou
 */
public class Publication {

    /**field id*/
    private int id;

    /**field author*/
    private String author;

    /**field name*/
    private String name;

    /**field language id*/
    private int languageId;

    /**field cost*/
    private int cost;

    /**
     * Constructor to create a specific object <b>without</b> id
     * @param author author name
     * @param name name of publication
     * @param languageId bound language id
     */
    public Publication(String author, String name, int cost, int languageId) {
        this.author = author;
        this.name = name;
        this.cost = cost;
        this.languageId = languageId;
    }

    /**
     * Constructor to create a specific object <b>with</b> id
     * @param id entity id
     * @param author author name
     * @param name name of publication
     * @param languageId bound language id
     */
    public Publication(int id, String author, String name, int cost, int languageId) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.cost = cost;
        this.languageId = languageId;
    }

    /**
     * get method of {@link Publication#id}
     * @return id of entity
     */
    public int getId() {
        return id;
    }

    /**
     * get method of {@link Publication#author}
     * @return author name
     */
    public String getAuthor() {
        return author;
    }

    /**
     * set method of {@link Publication#author}
     * @param author author name
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * get method of {@link Publication#name}
     * @return publication name
     */
    public String getName() {
        return name;
    }

    /**
     * set method of {@link Publication#name}
     * @param name publication name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get method of {@link Publication#cost}
     * @return  cost publication per month
     */
    public int getCost() {
        return cost;
    }

    /**
     * set method of {@link Publication#cost}
     * @param cost cost publication per month
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * get method of {@link Publication#languageId}
     * @return bound language id
     */
    public int getLanguageId() {
        return languageId;
    }

    /**
     * set method of {@link Publication#languageId}
     * @param languageId bound language id
     */
    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }
}
