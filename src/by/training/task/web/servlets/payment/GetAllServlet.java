package by.training.task.web.servlets.payment;

import by.training.task.dao.factory.DAOFactory;
import by.training.task.dao.interfaces.PaymentDAO;
import by.training.task.entities.Payment;
import by.training.task.locale.LocaleManager;
import by.training.task.utils.LoggerManager;
import by.training.task.web.sort.SortOrder;
import by.training.task.web.sort.enums.PaymentSortOrder;
import by.training.task.web.sort.util.PaymentSortUtil;
import by.training.task.web.utils.ListConfigUtil;
import by.training.task.web.utils.SessionUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet to get all of payment entities
 *
 * @author Anton Puhachou
 */
@WebServlet("/secure/getAllPayment")
public class GetAllServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocaleManager localeManager = LocaleManager.getInstance();
        String counterParam = req.getParameter("counter");
        String numberParam = req.getParameter("number");
        String sortParam = req.getParameter("sortOrder");
        int counter;
        int number;
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        PaymentDAO paymentDAO = factory.getPaymentDAO();
        List<Payment> entities = null;
        try {
            entities = paymentDAO.getAll();
        } catch (SQLException e) {
            LoggerManager loggerManager = LoggerManager.getInstance();
            loggerManager.error(this.getClass().toString(), e);
        }
        if (entities != null) {
            if (sortParam != null) {
                if (!sortParam.isEmpty()) {
                    SortOrder orderFromSession = SessionUtil.getSortOrderFromSession(req.getSession());
                    PaymentSortOrder oldOrder = orderFromSession instanceof PaymentSortOrder ?
                            (PaymentSortOrder) orderFromSession : PaymentSortOrder.NONE;
                    PaymentSortOrder newOrder = PaymentSortOrder.valueOf(sortParam, oldOrder);
                    SessionUtil.setSortOrderToSession(req.getSession(), newOrder);
                    entities = PaymentSortUtil.sort(entities, newOrder);
                } else {
                    SessionUtil.setSortOrderToSession(req.getSession(), PaymentSortOrder.NONE);
                }
            }
            if (entities.size() != 0) {
                int size = entities.size();
                if (counterParam == null || numberParam == null) {
                    counter = 0;
                    number = entities.size();
                } else {
                    counter = Integer.valueOf(counterParam);
                    number = Integer.valueOf(numberParam);
                }
                if (counter >= entities.size()) {
                    return;
                }
                entities = ListConfigUtil.getPartOfList(entities, counter, number);
                JSONArray jsonArray = new JSONArray();
                for (Payment current : entities) {
                    jsonArray.put(current.toLocaleString(localeManager.getLocale()));
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("size", size);
                jsonObject.put("payments", jsonArray);
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(jsonObject.toString());
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
