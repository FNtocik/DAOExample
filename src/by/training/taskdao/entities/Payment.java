package by.training.taskdao.entities;

/**
 * Payment class with properties <b>cost</b>, <b>reader ID</b>, <b>payed</b>
 * flag, <b>language</b> locale and read only id
 * @author Anton Puhachou
 */
public class Payment {

    /**field id*/
    private int id;

    /**field cost*/
    private int cost;

    /**field reader id*/
    private int readerId;

    /**field flag payed*/
    private boolean payed;

    /**field language id*/
    private int languageId;

    /**
     * Constructor to create a specific object <b>without</b> id
     * @param cost total cost of payment
     * @param readerId bound reader id
     * @param payed flag is payed
     * @param languageId bound language id
     */
    public Payment(int cost, int readerId, boolean payed, int languageId) {
        this.cost = cost;
        this.readerId = readerId;
        this.payed = payed;
        this.languageId = languageId;
    }

    /**
     * Constructor to create a specific object with id
     * @param id entity id
     * @param cost total cost of payment
     * @param readerId bound reader id
     * @param payed flag is payed
     * @param languageId bound language id
     */
    public Payment(int id, int cost, int readerId, boolean payed, int languageId) {
        this.id = id;
        this.cost = cost;
        this.readerId = readerId;
        this.payed = payed;
        this.languageId = languageId;
    }

    /**
     * get method of {@link Payment#id}
     * @return id of entity
     */
    public int getId() {
        return id;
    }

    /**
     * get method of {@link Payment#cost}
     * @return total cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * set method of {@link Payment#cost}
     * @param cost total cost
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * get method of {@link Payment#readerId}
     * @return bound reader id
     */
    public int getReaderId() {
        return readerId;
    }

    /**
     * set method of {@link Payment#readerId}
     * @param readerId bound reader id
     */
    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    /**
     * get method of {@link Payment#readerId}
     * @return bound reader id
     */
    public boolean isPayed() {
        return payed;
    }

    /**
     * set method of {@link Payment#payed}
     * @param payed flag is payed
     */
    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    /**
     * get method of {@link Payment#languageId}
     * @return bound language id
     */
    public int getLanguageId() {
        return languageId;
    }

    /**
     * set method of {@link Payment#languageId}
     * @param languageId bound language id
     */
    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }
}
