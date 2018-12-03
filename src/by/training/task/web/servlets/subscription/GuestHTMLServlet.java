package by.training.task.web.servlets.subscription;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/subscriptionGuest", name = "subscriptionGuest")
public class GuestHTMLServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = (String) req.getSession().getAttribute("template");
        String headers = (String) req.getAttribute("headers");
        html = html.replace("$title", "subscription");
        html = html.replace("$header", headers);
        html = html.replace("$control", "");
        html = html.replace("$scriptName", "subscription/" + getServletName());
        html = html.replace("$localeName", "subscription");
        resp.getWriter().write(html);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
