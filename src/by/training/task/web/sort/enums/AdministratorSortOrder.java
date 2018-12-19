package by.training.task.web.sort.enums;

import by.training.task.web.sort.SortOrder;

public enum AdministratorSortOrder implements SortOrder {

    NONE,
    ID_DESC, ID_ASC,
    LOGIN_DESC, LOGIN_ASC,
    PASSWORD_DESC, PASSWORD_ASC;

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
