package by.training.taskdao.web.servlets.locale;

import by.training.taskdao.web.config.LocaleStringsManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/locale")
public class GetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestedPage = req.getParameter("page");
        String jsonAnswer = "";
        LocaleStringsManager manager = LocaleStringsManager.getInstance();
        switch (requestedPage){
            case "login":
                jsonAnswer = manager.getLoginPageStrings();
                break;
            case "denied":
                jsonAnswer = manager.getDeniedPageStrings();
                break;
            default:
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                break;
        }
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonAnswer);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
