package by.training.task.web.servlets.administrator;

import by.training.task.dao.factory.DAOFactory;
import by.training.task.dao.interfaces.AdministratorDAO;
import by.training.task.entities.Administrator;
import by.training.task.utils.LoggerManager;
import by.training.task.web.sort.SortOrder;
import by.training.task.web.sort.enums.AdministratorSortOrder;
import by.training.task.web.sort.util.AdministratorSortUtil;
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
 * Servlet to get all of administrator entities
 *
 * @author Anton Puhachou
 */
@WebServlet("/secure/getAllAdministrator")
public class GetAllServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String counterParam = req.getParameter("counter");
        String numberParam = req.getParameter("number");
        String sortParam = req.getParameter("sortOrder");
        int counter;
        int number;
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        AdministratorDAO administratorDAO = factory.getAdministratorDAO();
        List<Administrator> entities = null;
        try {
            entities = administratorDAO.getAll();
        } catch (SQLException e) {
            LoggerManager loggerManager = LoggerManager.getInstance();
            loggerManager.error(this.getClass().toString(), e);
        }
        if (entities != null) {
            if (sortParam != null) {
                SortOrder orderFromSession = SessionUtil.getSortOrderFromSession(req.getSession());
                AdministratorSortOrder oldOrder = orderFromSession instanceof AdministratorSortOrder ?
                        (AdministratorSortOrder) orderFromSession : AdministratorSortOrder.NONE;
                AdministratorSortOrder newOrder = AdministratorSortOrder.valueOf(sortParam, oldOrder);
                SessionUtil.setSortOrderToSession(req.getSession(), newOrder);
                entities = AdministratorSortUtil.sort(entities, newOrder);
            } else {
                SessionUtil.setSortOrderToSession(req.getSession(), AdministratorSortOrder.NONE);
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
                for (Administrator current : entities) {
                    jsonArray.put(current.toString());
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("size", size);
                jsonObject.put("administrators", jsonArray);
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
