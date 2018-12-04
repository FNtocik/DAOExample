package by.training.task.web.servlets.administrator;

import by.training.task.dao.factory.DAOFactory;
import by.training.task.dao.interfaces.AdministratorDAO;
import by.training.task.entities.Administrator;
import by.training.task.utils.LoggerManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet to create administrator entity
 *
 * @author Anton Puhachou
 */
@WebServlet("/secure/addAdministrator")
public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        AdministratorDAO administratorDAO = daoFactory.getAdministratorDAO();
        String login = String.valueOf(req.getParameter("login"));
        String password = String.valueOf(req.getParameter("password"));
        Administrator entity = new Administrator(login, password);
        try {
            administratorDAO.create(entity);
        } catch (SQLException e) {
            LoggerManager loggerManager = LoggerManager.getInstance();
            loggerManager.error(this.getClass().toString(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
