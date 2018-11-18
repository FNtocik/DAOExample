package by.training.taskdao.web.servlets.subscription;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.PaymentDAO;
import by.training.taskdao.dao.interfaces.PublicationDAO;
import by.training.taskdao.dao.interfaces.ReaderDAO;
import by.training.taskdao.dao.interfaces.SubscriptionDAO;
import by.training.taskdao.entities.Payment;
import by.training.taskdao.entities.Publication;
import by.training.taskdao.entities.Reader;
import by.training.taskdao.entities.Subscription;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class SubscriptionsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        SubscriptionDAO subscriptionDAO = factory.getSubscriptionDAO();
        ReaderDAO readerDAO = factory.getReaderDAO();
        PaymentDAO paymentDAO = factory.getPaymentDAO();
        PublicationDAO publicationDAO = factory.getPublicationDAO();
        String jsonAnswer = "";
        String parameterId = req.getParameter("subscriptionId");
        if(parameterId == null){
            List<Subscription> entities = null;
            try {
                entities = subscriptionDAO.getAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(entities != null){
                JSONArray jsonArray = new JSONArray();
                for (Subscription current : entities) {
                    Reader currentReader = null;
                    Payment currentPayment = null;
                    Publication currnetPublication = null;
                    try {
                        currentReader = readerDAO.get(current.getReaderId());
                        currentPayment = paymentDAO.get(current.getPaymentId());
                        currnetPublication = publicationDAO.get(current.getPublicationId());
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                    JSONObject object = new JSONObject();
                    object.append("subscriptionId", current.getId());
                    object.append("readerId", currentReader.getLogin());
                    object.append("paymentId", currentPayment.getCost());
                    object.append("publicationId", currnetPublication.getName());
                    object.append("startDate", current.getStartSubscription().toString());
                    object.append("endDate", current.getEndSubscription().toString());
                    object.append("languageId", current.getLanguageId());
                    jsonArray.put(object);
                }
                jsonAnswer = jsonArray.toString();
            }
        }
        else{
            int subscriptionId = Integer.valueOf(parameterId);
            Subscription entity = null;
            try {
                entity = subscriptionDAO.get(subscriptionId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("subscriptionId", entity.getId());
            jsonObject.put("readerId", entity.getReaderId());
            jsonObject.put("publicationId", entity.getPublicationId());
            jsonObject.put("paymentId", entity.getPaymentId());
            jsonObject.put("languageId", entity.getLanguageId());
            jsonObject.put("startDate", entity.getStartSubscription().toString());
            jsonObject.put("endDate", entity.getEndSubscription().toString());
            jsonAnswer = jsonObject.toString();
        }
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonAnswer);
    }

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
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        Subscription entity = new Subscription(readerId,
                publicationId, paymentId,
                startDate, endDate, languageId);
        try {
            subscriptionDAO.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int subscriptionId = Integer.valueOf(String.valueOf(req.getParameter(
                "subscriptionId")));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        SubscriptionDAO subscriptionDAO = daoFactory.getSubscriptionDAO();
        try {
            subscriptionDAO.delete(subscriptionId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
