package by.training.taskdao.web.servlets.subscription;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.SubscriptionDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int subscriptionId = Integer.valueOf(String.valueOf(req.getParameter(
                "subscriptionId")));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        SubscriptionDAO subscriptionDAO = daoFactory.getSubscriptionDAO();
        try {
            subscriptionDAO.delete(subscriptionId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
