package by.training.task.web.sort.util;

import by.training.task.entities.Administrator;
import by.training.task.web.sort.enums.AdministratorSortOrder;

import java.util.Comparator;
import java.util.List;

/**
 * Util class to sort {@link List} of {@link Administrator}'s in certain {@link AdministratorSortOrder}
 *
 * @author Anton Puhachou
 */
public class AdministratorSortUtil {

    /**
     * Static method of sorting in certain {@link AdministratorSortOrder}
     *
     * @param entities  list to sort
     * @param sortOrder sort order
     * @return sorted list of entities
     */
    public static List<Administrator> sort(List<Administrator> entities, AdministratorSortOrder sortOrder) {
        switch (sortOrder) {
            case ID_ASC:
                entities.sort(new Comparator<Administrator>() {
                    @Override
                    public int compare(Administrator o1, Administrator o2) {
                        return Integer.compare(o1.getId(), o2.getId());
                    }
                });
                break;
            case ID_DESC:
                entities.sort(new Comparator<Administrator>() {
                    @Override
                    public int compare(Administrator o1, Administrator o2) {
                        return -1 * Integer.compare(o1.getId(), o2.getId());
                    }
                });
                break;
            case LOGIN_ASC:
                entities.sort(new Comparator<Administrator>() {
                    @Override
                    public int compare(Administrator o1, Administrator o2) {
                        return o1.getLogin().compareTo(o2.getLogin());
                    }
                });
                break;
            case LOGIN_DESC:
                entities.sort(new Comparator<Administrator>() {
                    @Override
                    public int compare(Administrator o1, Administrator o2) {
                        return -1 * o1.getLogin().compareTo(o2.getLogin());
                    }
                });
                break;
            case PASSWORD_ASC:
                entities.sort(new Comparator<Administrator>() {
                    @Override
                    public int compare(Administrator o1, Administrator o2) {
                        return o1.getPassword().compareTo(o2.getPassword());
                    }
                });
                break;
            case PASSWORD_DESC:
                entities.sort(new Comparator<Administrator>() {
                    @Override
                    public int compare(Administrator o1, Administrator o2) {
                        return -1 * o1.getPassword().compareTo(o2.getPassword());
                    }
                });
                break;
        }
        return entities;
    }
}
