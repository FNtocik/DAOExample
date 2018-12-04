package by.training.task.web.servlets.publication;

import by.training.task.dao.factory.DAOFactory;
import by.training.task.dao.interfaces.PublicationDAO;
import by.training.task.entities.Publication;
import by.training.task.utils.LoggerManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet to get specific publication entity
 *
 * @author Anton Puhachou
 */
@WebServlet("/secure/getPublication")
public class GetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        PublicationDAO publicationDAO = factory.getPublicationDAO();
        String parameterId = req.getParameter("publicationId");
        if (parameterId != null) {
            int publicationId = Integer.valueOf(parameterId);
            Publication entity = null;
            try {
                entity = publicationDAO.get(publicationId);
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
