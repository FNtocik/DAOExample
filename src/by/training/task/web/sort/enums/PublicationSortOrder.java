package by.training.task.web.sort.enums;

import by.training.task.web.sort.SortOrder;

public enum PublicationSortOrder implements SortOrder {

    NONE,
    ID_DESC, ID_ASC,
    AUTHOR_DESC, AUTHOR_ASC,
    NAME_DESC, NAME_ASC,
    LANGUAGE_DESC, LANGUAGE_ASC,
    COST_DESC, COST_ASC;

    public static PublicationSortOrder valueOf(String value, PublicationSortOrder oldOrder) {
        PublicationSortOrder newOrder;
        switch (value.toLowerCase()) {
            case "id":
                newOrder = ID_ASC;
                break;
            case "author":
                newOrder = AUTHOR_ASC;
                break;
            case "name":
                newOrder = NAME_ASC;
                break;
            case "language":
                newOrder = LANGUAGE_ASC;
                break;
            case "cost":
                newOrder = COST_ASC;
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
    public PublicationSortOrder reversalOrder() {
        switch (this) {
            case ID_ASC:
                return ID_DESC;
            case ID_DESC:
                return ID_ASC;
            case AUTHOR_ASC:
                return AUTHOR_DESC;
            case AUTHOR_DESC:
                return AUTHOR_ASC;
            case NAME_ASC:
                return NAME_DESC;
            case NAME_DESC:
                return NAME_ASC;
            case LANGUAGE_ASC:
                return LANGUAGE_DESC;
            case LANGUAGE_DESC:
                return LANGUAGE_ASC;
            case COST_ASC:
                return COST_DESC;
            case COST_DESC:
                return COST_ASC;
            default:
                return NONE;
        }
    }
}
