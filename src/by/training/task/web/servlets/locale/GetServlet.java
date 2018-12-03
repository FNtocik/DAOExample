package by.training.task.web.servlets.locale;

import by.training.task.locale.LocaleManager;
import by.training.task.utils.FileReaderUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/locale")
public class GetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String fileName = "lang_";
        for (Cookie current : cookies) {
            if (current.getName().equalsIgnoreCase("local")) {
                fileName += current.getValue();
                break;
            }
        }
        fileName += ".xml";
        LocaleManager localeManager = LocaleManager.getInstance();
        String xmlLangs = FileReaderUtil.readAllFromFile(localeManager.getPathToLocale(), fileName);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/xml");
        resp.getWriter().write(xmlLangs);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
