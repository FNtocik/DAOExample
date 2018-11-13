package by.training.taskdao.servlets.subscription;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.SubscriptionDAO;
import by.training.taskdao.entities.Subscription;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class EditServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int subscriptionId = Integer.valueOf(String.valueOf(req.getParameter(
                                                        "subscriptionId")));
        int readerId = Integer.valueOf(String.valueOf(req.getParameter(
                                                    "readerId")));
        int publicationId = Integer.valueOf(String.valueOf(req.getParameter(
                                                        "publicationId")));
        int paymentId = Integer.valueOf(String.valueOf(req.getParameter(
                                                    "paymentId")));
        int languageId = Integer.valueOf(String.valueOf(req.getParameter(
                                                    "languageId")));
        Date startDate = Date.valueOf(String.valueOf(req.getParameter(
                                                    "startDate")));
        Date endDate = Date.valueOf(String.valueOf(req.getParameter("endDate")));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        SubscriptionDAO subscriptionDAO = daoFactory.getSubscriptionDAO();
        Subscription entity = new Subscription(subscriptionId,
                                                        readerId, publicationId,
                                                        paymentId, startDate,
                                                        endDate, languageId);
        try {
            subscriptionDAO.update(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.getWriter().write("Done");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
