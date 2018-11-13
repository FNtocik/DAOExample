package by.training.taskdao.servlets.payment;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.PaymentDAO;
import by.training.taskdao.dao.interfaces.SubscriptionDAO;
import by.training.taskdao.entities.Payment;
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
        int paymentId = Integer.valueOf(req.getParameter("paymentId"));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        PaymentDAO paymentDAO = daoFactory.getPaymentDAO();
        Payment entity = null;
        try {
            entity = paymentDAO.get(paymentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject jsonAnswer = new JSONObject();
        jsonAnswer.append("paymentId", entity.getId());
        jsonAnswer.append("cost", entity.getCost());
        jsonAnswer.append("isPayed", entity.isPayed());
        jsonAnswer.append("languageId", entity.getLanguageId());
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonAnswer.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
