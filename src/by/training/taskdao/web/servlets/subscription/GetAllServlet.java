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
import java.sql.SQLException;
import java.util.List;

public class GetAllServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        SubscriptionDAO subscriptionDAO = factory.getSubscriptionDAO();
        ReaderDAO readerDAO = factory.getReaderDAO();
        PaymentDAO paymentDAO = factory.getPaymentDAO();
        PublicationDAO publicationDAO = factory.getPublicationDAO();
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
            resp.getWriter().write(jsonArray.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
