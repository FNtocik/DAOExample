package by.training.task.web.utils;

import by.training.task.entities.LoggedUser;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    public static void saveLoggedUserToSession(HttpSession session, LoggedUser user){
        session.setAttribute("loggedUser", user);
    }

    public static LoggedUser getLoggedUserFromSession(HttpSession session){
        return (LoggedUser)session.getAttribute("loggedUser");
    }
}
