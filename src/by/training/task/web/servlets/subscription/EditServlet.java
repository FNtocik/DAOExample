package by.training.task.web.servlets.subscription;

import by.training.task.dao.factory.DAOFactory;
import by.training.task.dao.interfaces.SubscriptionDAO;
import by.training.task.entities.Subscription;
import by.training.task.utils.LoggerManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Servlet to edit subscription entity
 *
 * @author Anton Puhachou
 */
@WebServlet("/secure/editSubscription")
public class EditServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int subscriptionId = Integer.valueOf(String.valueOf(req.getParameter(
                "subscriptionId")));
        int readerId = Integer.valueOf(String.valueOf(req.getParameter(
                "readerId")));
        int publicationId = Integer.valueOf(String.valueOf(req.getParameter(
                "publicationId")));
        int paymentId = Integer.valueOf(String.valueOf(req.getParameter(
                "paymentId")));
        Date startDate = Date.valueOf(String.valueOf(req.getParameter(
                "startDate")));
        Date endDate = Date.valueOf(String.valueOf(req.getParameter("endDate")));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        SubscriptionDAO subscriptionDAO = daoFactory.getSubscriptionDAO();
        Subscription entity = new Subscription(subscriptionId,
                readerId, publicationId,
                paymentId, startDate,
                endDate);
        try {
            subscriptionDAO.update(entity);
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
