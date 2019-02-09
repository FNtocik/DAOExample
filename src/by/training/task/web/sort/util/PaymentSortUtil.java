package by.training.task.web.sort.util;

import by.training.task.entities.Payment;
import by.training.task.web.sort.enums.PaymentSortOrder;

import java.util.Comparator;
import java.util.List;

/**
 * Util class to sort {@link List} of {@link Payment}'s in certain {@link PaymentSortOrder}
 *
 * @author Anton Puhachou
 */
public class PaymentSortUtil {

    /**
     * Static method of sorting in certain {@link PaymentSortOrder}
     *
     * @param entities  list to sort
     * @param sortOrder sort order
     * @return sorted list of entities
     */
    public static List<Payment> sort(List<Payment> entities, PaymentSortOrder sortOrder) {
        switch (sortOrder) {
            case ID_ASC:
                entities.sort(new Comparator<Payment>() {
                    @Override
                    public int compare(Payment o1, Payment o2) {
                        return Integer.compare(o1.getId(), o2.getId());
                    }
                });
                break;
            case ID_DESC:
                entities.sort(new Comparator<Payment>() {
                    @Override
                    public int compare(Payment o1, Payment o2) {
                        return -1 * Integer.compare(o1.getId(), o2.getId());
                    }
                });
                break;
            case COST_ASC:
                entities.sort(new Comparator<Payment>() {
                    @Override
                    public int compare(Payment o1, Payment o2) {
                        return Integer.compare(o1.getCost(), o2.getCost());
                    }
                });
                break;
            case COST_DESC:
                entities.sort(new Comparator<Payment>() {
                    @Override
                    public int compare(Payment o1, Payment o2) {
                        return -1 * Integer.compare(o1.getCost(), o2.getCost());
                    }
                });
                break;
            case PAYED_ASC:
                entities.sort(new Comparator<Payment>() {
                    @Override
                    public int compare(Payment o1, Payment o2) {
                        return Boolean.compare(o1.isPayed(), o2.isPayed());
                    }
                });
                break;
            case PAYED_DESC:
                entities.sort(new Comparator<Payment>() {
                    @Override
                    public int compare(Payment o1, Payment o2) {
                        return -1 * Boolean.compare(o1.isPayed(), o2.isPayed());
                    }
                });
        }
        return entities;
    }
}
