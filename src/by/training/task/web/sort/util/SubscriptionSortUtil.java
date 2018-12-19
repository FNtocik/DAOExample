package by.training.task.web.sort.util;

import by.training.task.entities.Subscription;
import by.training.task.web.sort.enums.SubscriptionSortOrder;

import java.util.Comparator;
import java.util.List;

public class SubscriptionSortUtil {

    public static List<Subscription> sort(List<Subscription> entities, SubscriptionSortOrder sortOrder) {
        switch (sortOrder) {
            case ID_ASC:
                entities.sort(new Comparator<Subscription>() {
                    @Override
                    public int compare(Subscription o1, Subscription o2) {
                        return Integer.compare(o1.getId(), o2.getId());
                    }
                });
                break;
            case ID_DESC:
                entities.sort(new Comparator<Subscription>() {
                    @Override
                    public int compare(Subscription o1, Subscription o2) {
                        return -1 * Integer.compare(o1.getId(), o2.getId());
                    }
                });
                break;
            case READER_ASC:
                entities.sort(new Comparator<Subscription>() {
                    @Override
                    public int compare(Subscription o1, Subscription o2) {
                        return o1.getReader().getLogin().compareTo(o2.getReader().getLogin());
                    }
                });
                break;
            case READER_DESC:
                entities.sort(new Comparator<Subscription>() {
                    @Override
                    public int compare(Subscription o1, Subscription o2) {
                        return -1 * o1.getReader().getLogin().compareTo(o2.getReader().getLogin());
                    }
                });
                break;
            case PUBLICATION_ASC:
                entities.sort(new Comparator<Subscription>() {
                    @Override
                    public int compare(Subscription o1, Subscription o2) {
                        return o1.getPublication().getName().compareTo(o2.getPublication().getName());
                    }
                });
                break;
            case PUBLICATION_DESC:
                entities.sort(new Comparator<Subscription>() {
                    @Override
                    public int compare(Subscription o1, Subscription o2) {
                        return -1 * o1.getPublication().getName().compareTo(o2.getPublication().getName());
                    }
                });
                break;
            case PAYMENT_ASC:
                entities.sort(new Comparator<Subscription>() {
                    @Override
                    public int compare(Subscription o1, Subscription o2) {
                        return Integer.compare(o1.getPayment().getCost(), o2.getPayment().getCost());
                    }
                });
                break;
            case PAYMENT_DESC:
                entities.sort(new Comparator<Subscription>() {
                    @Override
                    public int compare(Subscription o1, Subscription o2) {
                        return -1 * Integer.compare(o1.getPayment().getCost(), o2.getPayment().getCost());
                    }
                });
                break;
            case START_SUBSCRIPTION_ASC:
                entities.sort(new Comparator<Subscription>() {
                    @Override
                    public int compare(Subscription o1, Subscription o2) {
                        return o1.getStartSubscription().compareTo(o2.getStartSubscription());
                    }
                });
                break;
            case START_SUBSCRIPTION_DESC:
                entities.sort(new Comparator<Subscription>() {
                    @Override
                    public int compare(Subscription o1, Subscription o2) {
                        return -1 * o1.getStartSubscription().compareTo(o2.getStartSubscription());
                    }
                });
                break;
            case END_SUBSCRIPTION_ASC:
                entities.sort(new Comparator<Subscription>() {
                    @Override
                    public int compare(Subscription o1, Subscription o2) {
                        return o1.getEndSubscription().compareTo(o2.getEndSubscription());
                    }
                });
                break;
            case END_SUBSCRIPTION_DESC:
                entities.sort(new Comparator<Subscription>() {
                    @Override
                    public int compare(Subscription o1, Subscription o2) {
                        return -1 * o1.getEndSubscription().compareTo(o2.getEndSubscription());
                    }
                });
                break;
        }
        return entities;
    }
}
