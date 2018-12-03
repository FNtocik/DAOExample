package by.training.task.web.servlets.subscription;

import by.training.task.dao.factory.DAOFactory;
import by.training.task.dao.interfaces.SubscriptionDAO;
import by.training.task.entities.Subscription;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/secure/getSubscription")
public class GetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        SubscriptionDAO subscriptionDAO = factory.getSubscriptionDAO();
        String parameterId = req.getParameter("subscriptionId");
        if (parameterId != null) {
            int subscriptionId = Integer.valueOf(parameterId);
            Subscription entity = null;
            try {
                entity = subscriptionDAO.get(subscriptionId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (entity != null) {
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(entity.toString());
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
