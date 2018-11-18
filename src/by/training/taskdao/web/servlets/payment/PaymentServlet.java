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

public class PaymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        PaymentDAO paymentDAO = factory.getPaymentDAO();
        String jsonAnswer = "";
        String parameterId = req.getParameter("paymentId");
        if(parameterId == null) {
            List<Payment> entities = null;
            try {
                entities = paymentDAO.getAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (entities != null) {
                JSONArray jsonArray = new JSONArray();
                for (Payment current : entities) {
                    JSONObject object = new JSONObject();
                    object.append("paymentId", current.getId());
                    object.append("cost", current.getCost());
                    object.append("isPayed", current.isPayed());
                    object.append("languageId", current.getLanguageId());
                    jsonArray.put(object);
                }
                jsonAnswer = jsonArray.toString();
            }
        }
        else{
            int paymentId = Integer.valueOf(parameterId);
            Payment entity = null;
            try {
                entity = paymentDAO.get(paymentId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.append("paymentId", entity.getId());
            jsonObject.append("cost", entity.getCost());
            jsonObject.append("isPayed", entity.isPayed());
            jsonObject.append("languageId", entity.getLanguageId());
            jsonAnswer = jsonObject.toString();
        }
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonAnswer);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int paymentId = Integer.valueOf(String.valueOf(req.getParameter(
                "paymentId")));
        int cost = Integer.valueOf(String.valueOf(req.getParameter("cost")));
        boolean isPayed = Boolean.valueOf(String.valueOf(req.getParameter(
                "isPayed")));
        int languageId = Integer.valueOf(String.valueOf(req.getParameter(
                "languageId")));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        PaymentDAO paymentDAO = daoFactory.getPaymentDAO();
        Payment entity = new Payment(paymentId, cost, isPayed, languageId);
        try {
            paymentDAO.update(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cost = Integer.valueOf(String.valueOf(req.getParameter("cost")));
        boolean isPayed = Boolean.valueOf(String.valueOf(req.getParameter(
                "isPayed")));
        int languageId = Integer.valueOf(String.valueOf(req.getParameter(
                "languageId")));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        PaymentDAO paymentDAO = daoFactory.getPaymentDAO();
        Payment entity = new Payment(cost, isPayed, languageId);
        try {
            paymentDAO.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int paymentId = Integer.valueOf(String.valueOf(req.getParameter("paymentId")));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        PaymentDAO paymentDAO = daoFactory.getPaymentDAO();
        try {
            paymentDAO.delete(paymentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
