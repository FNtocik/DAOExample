package by.training.task.web.sort.enums;

import by.training.task.web.sort.SortOrder;

/**
 * Enum with possible sort order to administrator table
 *
 * @author Anton Puhachou
 * @see by.training.task.web.sort.SortOrder
 */
public enum AdministratorSortOrder implements SortOrder {

    NONE,
    ID_DESC, ID_ASC,
    LOGIN_DESC, LOGIN_ASC,
    PASSWORD_DESC, PASSWORD_ASC;

    /**
     * get sort order by name of new sort order and previous sort order
     *
     * @param value    name of order in String
     * @param oldOrder previous sort order
     * @return new sort order
     */
    public static AdministratorSortOrder valueOf(String value, AdministratorSortOrder oldOrder) {
        AdministratorSortOrder newOrder;
        switch (value.toLowerCase()) {
            case "id":
                newOrder = ID_ASC;
                break;
            case "login":
                newOrder = LOGIN_ASC;
                break;
            case "password":
                newOrder = PASSWORD_ASC;
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
    public AdministratorSortOrder reversalOrder() {
        switch (this) {
            case ID_ASC:
                return ID_DESC;
            case ID_DESC:
                return ID_ASC;
            case LOGIN_ASC:
                return LOGIN_DESC;
            case LOGIN_DESC:
                return LOGIN_ASC;
            case PASSWORD_ASC:
                return PASSWORD_DESC;
            case PASSWORD_DESC:
                return PASSWORD_ASC;
            default:
                return NONE;
        }
    }

}
