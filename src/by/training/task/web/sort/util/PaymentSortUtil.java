package by.training.task.web.sort.util;

import by.training.task.entities.Payment;
import by.training.task.web.sort.enums.PaymentSortOrder;

import java.util.Comparator;
import java.util.List;

public class PaymentSortUtil {

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
