package by.training.task.web.servlets.subscription;

import by.training.task.utils.FileReaderUtil;
import by.training.task.web.config.TemplateFolderManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/subscriptionReader", name = "subscriptionReader")
public class ReaderHTMLServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = (String) req.getSession().getAttribute("template");
        String controls = (String) req.getAttribute("controls");
        String headers = (String) req.getAttribute("headers");
        String path = TemplateFolderManager.getInstance().getPathInfo();
        String buttons = FileReaderUtil.readAllFromFile(path, "controlButton.html");
        buttons = buttons.replace("$onclick", "add()");
        buttons = buttons.replace("$idButton", "addButton");
        controls += buttons;
        html = html.replace("$title", "Subscription");
        html = html.replace("$header", headers);
        html = html.replace("$control", controls);
        html = html.replace("$scriptName", "subscription/" + getServletName());
        html = html.replace("$localeName", "subscription");
        resp.getWriter().write(html);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
