package by.training.task.web.servlets.subscription;

import by.training.task.dao.factory.DAOFactory;
import by.training.task.dao.interfaces.SubscriptionDAO;
import by.training.task.entities.Subscription;
import by.training.task.utils.LoggerManager;
import by.training.task.web.sort.enums.SubscriptionSortOrder;
import by.training.task.web.sort.util.SubscriptionSortUtil;
import by.training.task.web.utils.ListConfigUtil;
import by.training.task.web.utils.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet to get specific subscription entity
 *
 * @author Anton Puhachou
 */
@WebServlet("/secure/getSubscription")
public class GetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        SubscriptionDAO subscriptionDAO = factory.getSubscriptionDAO();
        String counterParam = req.getParameter("counter");
        String numberParam = req.getParameter("number");
        SubscriptionSortOrder sortOrderFromSession = (SubscriptionSortOrder) SessionUtil.getSortOrderFromSession(req.getSession());
        int counter;
        int number;
        String parameterIndex = req.getParameter("subscriptionIndex");
        if (parameterIndex != null) {
            int subscriptionIndex = Integer.valueOf(parameterIndex);
            Subscription entity = null;
            List<Subscription> entities = null;
            try {
                entities = subscriptionDAO.getAll();
            } catch (SQLException e) {
                LoggerManager loggerManager = LoggerManager.getInstance();
                loggerManager.error(this.getClass().toString(), e);
            }
            if (entities != null) {
                if (sortOrderFromSession != null) {
                    SubscriptionSortUtil.sort(entities, sortOrderFromSession);
                }
                if (entities.size() != 0) {
                    if (counterParam == null || numberParam == null) {
                        counter = 0;
                        number = entities.size();
                    } else {
                        counter = Integer.valueOf(counterParam);
                        number = Integer.valueOf(numberParam);
                    }
                    entities = ListConfigUtil.getPartOfList(entities, counter, number);
                    entity = entities.get(subscriptionIndex);
                    if (entity != null) {
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(entity.toString());
                    }
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
