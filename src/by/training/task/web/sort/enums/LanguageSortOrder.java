package by.training.task.web.sort.enums;

import by.training.task.web.sort.SortOrder;

/**
 * Enum with possible sort order to language table
 *
 * @author Anton Puhachou
 * @see by.training.task.web.sort.SortOrder
 */
public enum LanguageSortOrder implements SortOrder {

    NONE,
    ID_DESC, ID_ASC,
    SIGNATURE_DESC, SIGNATURE_ASC;

    /**
     * get sort order by name of new sort order and previous sort order
     *
     * @param value    name of order in String
     * @param oldOrder previous sort order
     * @return new sort order
     */
    public static LanguageSortOrder valueOf(String value, LanguageSortOrder oldOrder) {
        LanguageSortOrder newOrder;
        switch (value.toLowerCase()) {
            case "id":
                newOrder = ID_ASC;
                break;
            case "signature":
                newOrder = SIGNATURE_ASC;
                break;
            default:
                newOrder = NONE;
                break;
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
    public LanguageSortOrder reversalOrder() {
        switch (this) {
            case ID_ASC:
                return ID_DESC;
            case ID_DESC:
                return ID_ASC;
            case SIGNATURE_ASC:
                return SIGNATURE_DESC;
            case SIGNATURE_DESC:
                return SIGNATURE_ASC;
            default:
                return NONE;
        }
    }
}
