package by.training.task.entities;

import org.json.JSONObject;

/**
 * Language class with property <b>signature</b> and read only id
 * @author Anton Puhachou
 */
public class Language {

    /**field id*/
    private int id;

    /**field signature*/
    private String signature;

    /**
     * Constructor to create a specific object <b>without</b> id
     * @param signature language signature
     * @see Language#Language(int, String)
     */
    public Language(String signature) {
        this.signature = signature;
    }

    /**
     * Constructor to create a specific object <b>with</b> id
     * @param id entity id
     * @param signature language signature
     * @see Language#Language(String)
     */
    public Language(int id, String signature) {
        this.id = id;
        this.signature = signature;
    }

    /**
     * get method of {@link Language#id}
     * @return id of entity
     */
    public int getId() {
        return id;
    }

    /**
     * get method of {@link Language#signature}
     * @return language signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * set method of {@link Language#signature}
     * @param signature language signature
     */
    public void setSignature(String signature) {
        this.signature = signature;
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
        jsonObject.put("signature", signature);
        return jsonObject.toString();
    }
}
