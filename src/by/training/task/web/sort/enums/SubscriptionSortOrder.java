package by.training.task.web.sort.enums;

import by.training.task.web.sort.SortOrder;

public enum SubscriptionSortOrder implements SortOrder {
    NONE,
    ID_DESC, ID_ASC,
    READER_DESC, READER_ASC,
    PUBLICATION_DESC, PUBLICATION_ASC,
    PAYMENT_DESC, PAYMENT_ASC,
    START_SUBSCRIPTION_DESC, START_SUBSCRIPTION_ASC,
    END_SUBSCRIPTION_DESC, END_SUBSCRIPTION_ASC;

    public static SubscriptionSortOrder valueOf(String value, SubscriptionSortOrder oldOrder) {
        SubscriptionSortOrder newOrder;
        switch (value.toLowerCase()) {
            case "id":
                newOrder = ID_ASC;
                break;
            case "reader":
                newOrder = READER_ASC;
                break;
            case "publication":
                newOrder = PUBLICATION_ASC;
                break;
            case "payment":
                newOrder = PAYMENT_ASC;
                break;
            case "startdate":
                newOrder = START_SUBSCRIPTION_ASC;
                break;
            case "enddate":
                newOrder = END_SUBSCRIPTION_ASC;
                break;
            default:
                newOrder = NONE;
        }
        if (newOrder == oldOrder) {
            return newOrder.reversalOrder();
        } else {
            return newOrder;
        }
    }

    @Override
    public SubscriptionSortOrder reversalOrder() {
        switch (this) {
            case ID_ASC:
                return ID_DESC;
            case ID_DESC:
                return ID_ASC;
            case READER_ASC:
                return READER_DESC;
            case READER_DESC:
                return READER_ASC;
            case PUBLICATION_ASC:
                return PUBLICATION_DESC;
            case PUBLICATION_DESC:
                return PUBLICATION_ASC;
            case PAYMENT_ASC:
                return PAYMENT_DESC;
            case PAYMENT_DESC:
                return PAYMENT_ASC;
            case START_SUBSCRIPTION_ASC:
                return START_SUBSCRIPTION_DESC;
            case START_SUBSCRIPTION_DESC:
                return START_SUBSCRIPTION_ASC;
            case END_SUBSCRIPTION_DESC:
                return END_SUBSCRIPTION_ASC;
            case END_SUBSCRIPTION_ASC:
                return END_SUBSCRIPTION_DESC;
            default:
                return NONE;
        }
    }
}
