package by.training.task.web.sort;

/**
 * Interface for sort order enums
 *
 * @author Anton Puhachou
 */
public interface SortOrder {
    /***
     * Method to obtain reversed sort order to this sort order
     * @return backward order
     */
    SortOrder reversalOrder();
}
