package by.training.taskdao.web.servlets.payment;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.PaymentDAO;
import by.training.taskdao.entities.Payment;
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
        PaymentDAO paymentDAO = factory.getPaymentDAO();
        List<Payment> entities = null;
        try {
            entities = paymentDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(entities != null){
            JSONArray jsonArray = new JSONArray();
            for (Payment current : entities) {
                JSONObject object = new JSONObject();
                object.append("paymentId", current.getId());
                object.append("cost", current.getCost());
                object.append("isPayed", current.isPayed());
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
