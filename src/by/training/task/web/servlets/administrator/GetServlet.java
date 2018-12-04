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

@WebServlet("/secure/getAdministrator")
public class GetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        AdministratorDAO administratorDAO = factory.getAdministratorDAO();
        String parameterId = req.getParameter("administratorId");
        if (parameterId != null) {
            int administratorId = Integer.valueOf(parameterId);
            Administrator entity = null;
            try {
                entity = administratorDAO.get(administratorId);
            } catch (SQLException e) {
                LoggerManager loggerManager = LoggerManager.getInstance();
                loggerManager.error(this.getClass().toString(), e);
            }
            if (entity != null) {
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(entity.toString());
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
