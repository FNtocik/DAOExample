package by.training.taskdao.web.servlets.subscription;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.SubscriptionDAO;
import by.training.taskdao.entities.Subscription;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class GetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int subscriptionId = Integer.valueOf(req.getParameter("subscriptionId"));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        SubscriptionDAO subscriptionDAO = daoFactory.getSubscriptionDAO();
        Subscription entity = null;
        try {
            entity = subscriptionDAO.get(subscriptionId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject jsonAnswer = new JSONObject();
        jsonAnswer.put("subscriptionId", entity.getId());
        jsonAnswer.put("readerId", entity.getReaderId());
        jsonAnswer.put("publicationId", entity.getPublicationId());
        jsonAnswer.put("paymentId", entity.getPaymentId());
        jsonAnswer.put("languageId", entity.getLanguageId());
        jsonAnswer.put("startDate", entity.getStartSubscription().toString());
        jsonAnswer.put("endDate", entity.getEndSubscription().toString());
        resp.getWriter().write(jsonAnswer.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
