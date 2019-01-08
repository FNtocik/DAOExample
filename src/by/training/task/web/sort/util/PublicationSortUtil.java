package by.training.task.web.sort.util;

import by.training.task.entities.Publication;
import by.training.task.web.sort.enums.PublicationSortOrder;

import java.util.Comparator;
import java.util.List;

/**
 * Util class to sort {@link List} of {@link Publication}'s in certain {@link PublicationSortOrder}
 *
 * @author Anton Puhachou
 */
public class PublicationSortUtil {

    /**
     * Static method of sorting in certain {@link PublicationSortOrder}
     *
     * @param entities  list to sort
     * @param sortOrder sort order
     * @return sorted list of entities
     */
    public static List<Publication> sort(List<Publication> entities, PublicationSortOrder sortOrder) {
        switch (sortOrder) {
            case ID_ASC:
                entities.sort(new Comparator<Publication>() {
                    @Override
                    public int compare(Publication o1, Publication o2) {
                        return Integer.compare(o1.getId(), o2.getId());
                    }
                });
                break;
            case ID_DESC:
                entities.sort(new Comparator<Publication>() {
                    @Override
                    public int compare(Publication o1, Publication o2) {
                        return -1 * Integer.compare(o1.getId(), o2.getId());
                    }
                });
                break;
            case AUTHOR_ASC:
                entities.sort(new Comparator<Publication>() {
                    @Override
                    public int compare(Publication o1, Publication o2) {
                        return o1.getAuthor().compareTo(o2.getAuthor());
                    }
                });
                break;
            case AUTHOR_DESC:
                entities.sort(new Comparator<Publication>() {
                    @Override
                    public int compare(Publication o1, Publication o2) {
                        return -1 * o1.getAuthor().compareTo(o2.getAuthor());
                    }
                });
                break;
            case NAME_ASC:
                entities.sort(new Comparator<Publication>() {
                    @Override
                    public int compare(Publication o1, Publication o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
                break;
            case NAME_DESC:
                entities.sort(new Comparator<Publication>() {
                    @Override
                    public int compare(Publication o1, Publication o2) {
                        return -1 * o1.getName().compareTo(o2.getName());
                    }
                });
                break;
            case LANGUAGE_ASC:
                entities.sort(new Comparator<Publication>() {
                    @Override
                    public int compare(Publication o1, Publication o2) {
                        return o1.getLanguage().getSignature().compareTo(o2.getLanguage().getSignature());
                    }
                });
                break;
            case LANGUAGE_DESC:
                entities.sort(new Comparator<Publication>() {
                    @Override
                    public int compare(Publication o1, Publication o2) {
                        return -1 * o1.getLanguage().getSignature().compareTo(o2.getLanguage().getSignature());
                    }
                });
                break;
            case COST_ASC:
                entities.sort(new Comparator<Publication>() {
                    @Override
                    public int compare(Publication o1, Publication o2) {
                        return Integer.compare(o1.getCost(), o2.getCost());
                    }
                });
                break;
            case COST_DESC:
                entities.sort(new Comparator<Publication>() {
                    @Override
                    public int compare(Publication o1, Publication o2) {
                        return -1 * Integer.compare(o1.getCost(), o2.getCost());
                    }
                });
                break;
        }
        return entities;
    }
}
