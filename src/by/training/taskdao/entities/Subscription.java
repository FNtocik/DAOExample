package by.training.taskdao.entities;

import java.sql.Date;

/**
 * Subscription class with properties <b>reader ID</b>, <b>publication
 * ID</b>, <b>payment ID</b>, <b>start subscription</b> date, <b>end
 * subscription</b> date and <b>language</b> locale and read only id
 * @author Anton Puhachou
 */
public class Subscription {

    /**field id*/
    private int id;

    /**field reader id*/
    private int readerId;

    /**field publication id*/
    private int publicationId;

    /**field payment id*/
    private int paymentId;

    /**field start subscription date*/
    private Date startSubscription;

    /**field end subscription date*/
    private Date endSubscription;

    /**field language id*/
    private int languageId;

    /**
     * Constructor to create a specific object <b>without</b> id
     * @param readerId bound reader id
     * @param publicationId bound publication id
     * @param paymentId bound payment id
     * @param startSubscription start subscription date
     * @param endSubscription end subscription date
     * @param languageId bound language id
     * @see Subscription#Subscription(int, int, int, int, Date, Date, int)
     */
    public Subscription(int readerId, int publicationId, int paymentId, Date startSubscription, Date endSubscription, int languageId) {
        this.readerId = readerId;
        this.publicationId = publicationId;
        this.paymentId = paymentId;
        this.startSubscription = startSubscription;
        this.endSubscription = endSubscription;
        this.languageId = languageId;
    }

    /**
     * Constructor to create a specific object <b>with</b> id
     * @param id entity id
     * @param readerId bound reader id
     * @param publicationId bound publication id
     * @param paymentId bound payment id
     * @param startSubscription start subscription date
     * @param endSubscription end subscription date
     * @param languageId bound language id
     * @see Subscription#Subscription(int, int, int, int, Date, Date, int)
     */
    public Subscription(int id, int readerId, int publicationId, int paymentId, Date startSubscription, Date endSubscription, int languageId) {
        this.id = id;
        this.readerId = readerId;
        this.publicationId = publicationId;
        this.paymentId = paymentId;
        this.startSubscription = startSubscription;
        this.endSubscription = endSubscription;
        this.languageId = languageId;
    }

    /**
     * get method of {@link Subscription#id}
     * @return id of entity
     */
    public int getId() {
        return id;
    }

    /**
     * get method of {@link Subscription#readerId}
     * @return bound reader id
     */
    public int getReaderId() {
        return readerId;
    }

    /**
     * set method of {@link Subscription#readerId}
     * @param readerId bound reader id
     */
    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    /**
     * get method of {@link Subscription#publicationId}
     * @return bound publication id
     */
    public int getPublicationId() {
        return publicationId;
    }

    /**
     * set method of {@link Subscription#publicationId}
     * @param publicationId bound publication id
     */
    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    /**
     * get method of {@link Subscription#paymentId}
     * @return bound payment id
     */
    public int getPaymentId() {
        return paymentId;
    }

    /**
     * set method of {@link Subscription#paymentId}
     * @param paymentId bound payment id
     */
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * get method of {@link Subscription#startSubscription}
     * @return start subscription date
     */
    public Date getStartSubscription() {
        return startSubscription;
    }

    /**
     * set method of {@link Subscription#startSubscription}
     * @param startSubscription start subscription date
     */
    public void setStartSubscription(Date startSubscription) {
        this.startSubscription = startSubscription;
    }

    /**
     * get method of {@link Subscription#endSubscription}
     * @return end subscription date
     */
    public Date getEndSubscription() {
        return endSubscription;
    }

    /**
     * set method of {@link Subscription#endSubscription}
     * @param endSubscription end subscription date
     */
    public void setEndSubscription(Date endSubscription) {
        this.endSubscription = endSubscription;
    }

    /**
     * get method of {@link Subscription#languageId}
     * @return bound language id
     */
    public int getLanguageId() {
        return languageId;
    }

    /**
     * set method of {@link Subscription#languageId}
     * @param languageId bound language id
     */
    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }
}
