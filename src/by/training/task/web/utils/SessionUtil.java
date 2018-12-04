package by.training.task.web.utils;

import by.training.task.entities.LoggedUser;

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
}
