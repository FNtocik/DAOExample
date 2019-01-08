package by.training.task.web.sort.enums;

import by.training.task.web.sort.SortOrder;

/**
 * Enum with possible sort order to payment table
 *
 * @author Anton Puhachou
 * @see by.training.task.web.sort.SortOrder
 */
public enum PaymentSortOrder implements SortOrder {

    NONE,
    ID_DESC, ID_ASC,
    COST_DESC, COST_ASC,
    PAYED_DESC, PAYED_ASC;

    /**
     * get sort order by name of new sort order and previous sort order
     *
     * @param value    name of order in String
     * @param oldOrder previous sort order
     * @return new sort order
     */
    public static PaymentSortOrder valueOf(String value, PaymentSortOrder oldOrder) {
        PaymentSortOrder newOrder;
        switch (value.toLowerCase()) {
            case "id":
                newOrder = ID_ASC;
                break;
            case "cost":
                newOrder = COST_ASC;
                break;
            case "payed":
                newOrder = PAYED_ASC;
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

    /**
     * Method to obtain reversed sort order to this sort order
     *
     * @return backward order
     * @see SortOrder#reversalOrder()
     */
    @Override
    public PaymentSortOrder reversalOrder() {
        switch (this) {
            case ID_ASC:
                return ID_DESC;
            case ID_DESC:
                return ID_ASC;
            case COST_ASC:
                return COST_DESC;
            case COST_DESC:
                return COST_ASC;
            case PAYED_ASC:
                return PAYED_DESC;
            case PAYED_DESC:
                return PAYED_ASC;
            default:
                return NONE;
        }
    }
}
