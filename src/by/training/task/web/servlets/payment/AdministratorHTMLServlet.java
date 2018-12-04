package by.training.task.web.servlets.payment;

import by.training.task.utils.FileReaderUtil;
import by.training.task.web.config.TemplateFolderManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to get administrator html page
 *
 * @author Anton Puhachou
 */
@WebServlet(value = "/paymentAdministrator", name = "paymentAdministrator")
public class AdministratorHTMLServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = (String) req.getSession().getAttribute("template");
        String controls = (String) req.getAttribute("controls");
        String headers = (String) req.getAttribute("headers");
        String path = TemplateFolderManager.getInstance().getPathInfo();
        String buttons = FileReaderUtil.readAllFromFile(path, "controlButton.html");
        String allButtons = buttons;
        allButtons = allButtons.replace("$onclick", "add()");
        allButtons = allButtons.replace("$idButton", "addButton");
        allButtons += buttons;
        allButtons = allButtons.replace("$onclick", "edit()");
        allButtons = allButtons.replace("$idButton", "editButton");
        allButtons += buttons;
        allButtons = allButtons.replace("$onclick", "del()");
        allButtons = allButtons.replace("$idButton", "deleteButton");
        controls += allButtons;
        html = html.replace("$title", "payment");
        html = html.replace("$header", headers);
        html = html.replace("$control", controls);
        html = html.replace("$scriptName", "payment/" + getServletName());
        html = html.replace("$localeName", "payment");
        resp.getWriter().write(html);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
