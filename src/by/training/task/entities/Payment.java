package by.training.task.entities;

import org.json.JSONObject;

/**
 * Payment class with properties <b>cost</b>, <b>payed</b>
 * flag, <b>language</b> config and read only id
 * @author Anton Puhachou
 */
public class Payment {

    /**field id*/
    private int id;

    /**field cost*/
    private int cost;

    /**field flag payed*/
    private boolean payed;

    /**
     * Constructor to create a specific object <b>without</b> id
     * @param cost total cost of payment
     * @param payed flag is payed
     */
    public Payment(int cost, boolean payed) {
        this.cost = cost;
        this.payed = payed;
    }

    /**
     * Constructor to create a specific object with id
     * @param id entity id
     * @param cost total cost of payment
     * @param payed flag is payed
     */
    public Payment(int id, int cost, boolean payed) {
        this.id = id;
        this.cost = cost;
        this.payed = payed;
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
     * get method of {@link Payment#payed}
     * @return flag is payed
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

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("cost", cost);
        jsonObject.put("payed", payed);
        return jsonObject.toString();
    }
}
