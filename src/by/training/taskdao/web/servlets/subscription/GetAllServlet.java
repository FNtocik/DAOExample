package by.training.taskdao.web.servlets.subscription;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.SubscriptionDAO;
import by.training.taskdao.entities.Subscription;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/secure/getAllSubscription")
public class GetAllServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        SubscriptionDAO subscriptionDAO = factory.getSubscriptionDAO();
        List<Subscription> entities = null;
        try {
            entities = subscriptionDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (entities != null && entities.size() != 0) {
            JSONArray jsonArray = new JSONArray();
            for (Subscription current : entities) {
                jsonArray.put(current.toString());
            }
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonArray.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
