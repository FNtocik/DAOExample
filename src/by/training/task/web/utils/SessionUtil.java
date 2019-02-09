package by.training.task.web.utils;

import by.training.task.entities.LoggedUser;
import by.training.task.web.sort.SortOrder;

import javax.servlet.http.HttpSession;

/**
 * Session util class to set logged user in session
 *
 * @author Anton Puhachou
 */
public class SessionUtil {

    /**
     * Method to set logged user to session
     * */
    public static void saveLoggedUserToSession(HttpSession session, LoggedUser user){
        session.setAttribute("loggedUser", user);
    }

    /**
     * Method to get logged user from session
     * @return logged user object
     * */
    public static LoggedUser getLoggedUserFromSession(HttpSession session){
        return (LoggedUser)session.getAttribute("loggedUser");
    }

    public static void setSortOrderToSession(HttpSession session, SortOrder order) {
        session.setAttribute("sortOrder", order);
    }

    public static SortOrder getSortOrderFromSession(HttpSession session) {
        return (SortOrder) session.getAttribute("sortOrder");
    }
}
