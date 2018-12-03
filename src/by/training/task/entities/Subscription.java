package by.training.task.entities;

import org.json.JSONObject;

import java.sql.Date;

/**
 * Subscription class with properties <b>reader ID</b>, <b>publication
 * ID</b>, <b>payment ID</b>, <b>start subscription</b> date, <b>end
 * subscription</b> date and <b>language</b> config and read only id
 * @author Anton Puhachou
 */
public class Subscription {

    /**field id*/
    private int id;

    /**field reader id*/
    private int readerId;

    /**
     * field bound reader
     */
    private Reader reader;

    /**field publication id*/
    private int publicationId;

    /**
     * field bound publication
     */
    private Publication publication;

    /**field payment id*/
    private int paymentId;

    /**
     * field bound payment
     */
    private Payment payment;

    /**field start subscription date*/
    private Date startSubscription;

    /**field end subscription date*/
    private Date endSubscription;

    /**
     * Constructor to create a specific object <b>without</b> id
     * @param readerId bound reader id
     * @param publicationId bound publication id
     * @param paymentId bound payment id
     * @param startSubscription start subscription date
     * @param endSubscription end subscription date
     * @see Subscription#Subscription(int, int, int, int, Date, Date)
     */
    public Subscription(int readerId, int publicationId, int paymentId,
                        Date startSubscription, Date endSubscription) {
        this.readerId = readerId;
        this.publicationId = publicationId;
        this.paymentId = paymentId;
        this.startSubscription = startSubscription;
        this.endSubscription = endSubscription;
    }

    /**
     * Constructor to create a specific object <b>with</b> id
     * @param id entity id
     * @param readerId bound reader id
     * @param publicationId bound publication id
     * @param paymentId bound payment id
     * @param startSubscription start subscription date
     * @param endSubscription end subscription date
     * @see Subscription#Subscription(int, int, int, Date, Date)
     */
    public Subscription(int id, int readerId, int publicationId, int paymentId, Date startSubscription,
                        Date endSubscription) {
        this.id = id;
        this.readerId = readerId;
        this.publicationId = publicationId;
        this.paymentId = paymentId;
        this.startSubscription = startSubscription;
        this.endSubscription = endSubscription;
    }

    public Subscription(int id, Reader reader, Publication publication, Payment payment, Date startSubscription,
                        Date endSubscription) {
        this.id = id;
        this.reader = reader;
        this.readerId = reader.getId();
        this.publication = publication;
        this.publicationId = publication.getId();
        this.payment = payment;
        this.paymentId = payment.getId();
        this.startSubscription = startSubscription;
        this.endSubscription = endSubscription;
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

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
        this.readerId = reader.getId();
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

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
        this.publicationId = publication.getId();
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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
        this.paymentId = payment.getId();
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

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("reader", reader.toString());
        jsonObject.put("publication", publication.toString());
        jsonObject.put("payment", payment.toString());
        jsonObject.put("startDate", startSubscription.toString());
        jsonObject.put("endDate", endSubscription.toString());
        return jsonObject.toString();
    }
}
