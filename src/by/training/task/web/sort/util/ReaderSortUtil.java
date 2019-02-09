package by.training.task.web.sort.util;

import by.training.task.entities.Reader;
import by.training.task.web.sort.enums.ReaderSortOrder;

import java.util.Comparator;
import java.util.List;

/**
 * Util class to sort {@link List} of {@link Reader}'s in certain {@link ReaderSortOrder}
 *
 * @author Anton Puhachou
 */
public class ReaderSortUtil {

    /**
     * Static method of sorting in certain {@link ReaderSortOrder}
     *
     * @param entities  list to sort
     * @param sortOrder sort order
     * @return sorted list of entities
     */
    public static List<Reader> sort(List<Reader> entities, ReaderSortOrder sortOrder) {
        switch (sortOrder) {
            case ID_ASC:
                entities.sort(new Comparator<Reader>() {
                    @Override
                    public int compare(Reader o1, Reader o2) {
                        return Integer.compare(o1.getId(), o2.getId());
                    }
                });
                break;
            case ID_DESC:
                entities.sort(new Comparator<Reader>() {
                    @Override
                    public int compare(Reader o1, Reader o2) {
                        return -1 * Integer.compare(o1.getId(), o2.getId());
                    }
                });
                break;
            case LOGIN_ASC:
                entities.sort(new Comparator<Reader>() {
                    @Override
                    public int compare(Reader o1, Reader o2) {
                        return o1.getLogin().compareTo(o2.getLogin());
                    }
                });
                break;
            case LOGIN_DESC:
                entities.sort(new Comparator<Reader>() {
                    @Override
                    public int compare(Reader o1, Reader o2) {
                        return -1 * o1.getLogin().compareTo(o2.getLogin());
                    }
                });
                break;
            case PASSWORD_ASC:
                entities.sort(new Comparator<Reader>() {
                    @Override
                    public int compare(Reader o1, Reader o2) {
                        return o1.getPassword().compareTo(o2.getPassword());
                    }
                });
                break;
            case PASSWORD_DESC:
                entities.sort(new Comparator<Reader>() {
                    @Override
                    public int compare(Reader o1, Reader o2) {
                        return -1 * o1.getPassword().compareTo(o2.getPassword());
                    }
                });
                break;
            case LANGUAGE_ASC:
                entities.sort(new Comparator<Reader>() {
                    @Override
                    public int compare(Reader o1, Reader o2) {
                        return o1.getLanguage().getSignature().compareTo(o2.getLanguage().getSignature());
                    }
                });
                break;
            case LANGUAGE_DESC:
                entities.sort(new Comparator<Reader>() {
                    @Override
                    public int compare(Reader o1, Reader o2) {
                        return -1 * o1.getLanguage().getSignature().compareTo(o2.getLanguage().getSignature());
                    }
                });
                break;
        }
        return entities;
    }
}
