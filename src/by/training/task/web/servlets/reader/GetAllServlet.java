package by.training.task.web.servlets.reader;

import by.training.task.dao.factory.DAOFactory;
import by.training.task.dao.interfaces.ReaderDAO;
import by.training.task.entities.Reader;
import by.training.task.utils.LoggerManager;
import by.training.task.web.sort.SortOrder;
import by.training.task.web.sort.enums.ReaderSortOrder;
import by.training.task.web.sort.util.ReaderSortUtil;
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
 * Servlet to get all of reader entities
 *
 * @author Anton Puhachou
 */
@WebServlet("/secure/getAllReader")
public class GetAllServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String counterParam = req.getParameter("counter");
        String numberParam = req.getParameter("number");
        String sortParam = req.getParameter("sortOrder");
        int counter;
        int number;
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ReaderDAO readerDAO = factory.getReaderDAO();
        List<Reader> entities = null;
        try {
            entities = readerDAO.getAll();
        } catch (SQLException e) {
            LoggerManager loggerManager = LoggerManager.getInstance();
            loggerManager.error(this.getClass().toString(), e);
        }
        if (entities != null) {
            if (sortParam != null) {
                SortOrder orderFromSession = SessionUtil.getSortOrderFromSession(req.getSession());
                ReaderSortOrder oldOrder = orderFromSession instanceof ReaderSortOrder ?
                        (ReaderSortOrder) orderFromSession : ReaderSortOrder.NONE;
                ReaderSortOrder newOrder = ReaderSortOrder.valueOf(sortParam, oldOrder);
                SessionUtil.setSortOrderToSession(req.getSession(), newOrder);
                entities = ReaderSortUtil.sort(entities, newOrder);
            } else {
                SessionUtil.setSortOrderToSession(req.getSession(), ReaderSortOrder.NONE);
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
                for (Reader current : entities) {
                    jsonArray.put(current.toString());
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("size", size);
                jsonObject.put("readers", jsonArray);
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
