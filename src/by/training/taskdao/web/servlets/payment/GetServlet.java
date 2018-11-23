package by.training.taskdao.web.servlets.payment;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.PaymentDAO;
import by.training.taskdao.entities.Payment;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/secure/getPayment")
public class GetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        PaymentDAO paymentDAO = factory.getPaymentDAO();
        String parameterId = req.getParameter("paymentId");
        if (parameterId != null) {
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
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonObject.toString());
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
