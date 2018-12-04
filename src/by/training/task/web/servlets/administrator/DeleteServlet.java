package by.training.task.web.servlets.administrator;

import by.training.task.dao.factory.DAOFactory;
import by.training.task.dao.interfaces.AdministratorDAO;
import by.training.task.utils.LoggerManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet to delete administrator entity
 *
 * @author Anton Puhachou
 */
@WebServlet("/secure/deleteAdministrator")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int administratorId =
                Integer.valueOf(String.valueOf(req.getParameter("administratorId")));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        AdministratorDAO administratorDAO = daoFactory.getAdministratorDAO();
        try {
            administratorDAO.delete(administratorId);
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
