package by.training.taskdao.web.servlets.locale;

import by.training.taskdao.locale.LocaleManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/changeLocale")
public class EditServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String localeToSwitch = req.getParameter("locale");
        LocaleManager localeManager = LocaleManager.getInstance();
        localeToSwitch = localeManager.getSupportedLanguage(localeToSwitch);
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("local")) {
                cookie.setValue(localeToSwitch);
                resp.addCookie(cookie);
                break;
            }
        }
    }
}
