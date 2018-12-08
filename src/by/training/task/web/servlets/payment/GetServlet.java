package by.training.task.web.servlets.payment;

import by.training.task.dao.factory.DAOFactory;
import by.training.task.dao.interfaces.PaymentDAO;
import by.training.task.entities.Payment;
import by.training.task.utils.LoggerManager;
import by.training.task.web.utils.ListConfigUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet to get specific administrator entity
 *
 * @author Anton Puhachou
 */
@WebServlet("/secure/getPayment")
public class GetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        PaymentDAO paymentDAO = factory.getPaymentDAO();
        String counterParam = req.getParameter("counter");
        String numberParam = req.getParameter("number");
        int counter;
        int number;
        String parameterIndex = req.getParameter("paymentIndex");
        if (parameterIndex != null) {
            int paymentIndex = Integer.valueOf(parameterIndex);
            Payment entity = null;
            List<Payment> entities = null;
            try {
                entities = paymentDAO.getAll();
            } catch (SQLException e) {
                LoggerManager loggerManager = LoggerManager.getInstance();
                loggerManager.error(this.getClass().toString(), e);
            }
            if (entities != null && entities.size() != 0) {
                if (counterParam == null || numberParam == null) {
                    counter = 0;
                    number = entities.size();
                } else {
                    counter = Integer.valueOf(counterParam);
                    number = Integer.valueOf(numberParam);
                }
                entities = ListConfigUtil.getPartOfList(entities, counter, number);
                entity = entities.get(paymentIndex);
                if (entity != null) {
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().write(entity.toString());
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
