package by.training.taskdao.servlets.payment;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.PaymentDAO;
import by.training.taskdao.dao.interfaces.SubscriptionDAO;
import by.training.taskdao.entities.Payment;
import by.training.taskdao.entities.Subscription;

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
        resp.getWriter().write("Done");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
