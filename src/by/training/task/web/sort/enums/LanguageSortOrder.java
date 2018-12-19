package by.training.task.web.sort.enums;

import by.training.task.web.sort.SortOrder;

public enum LanguageSortOrder implements SortOrder {

    NONE,
    ID_DESC, ID_ASC,
    SIGNATURE_DESC, SIGNATURE_ASC;

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
