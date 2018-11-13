package by.training.taskdao.servlets.publication;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.PaymentDAO;
import by.training.taskdao.dao.interfaces.PublicationDAO;
import by.training.taskdao.entities.Payment;
import by.training.taskdao.entities.Publication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int publicationId = Integer.valueOf(String.valueOf(req.getParameter(
                                                        "publicationId")));
        String name = String.valueOf(req.getParameter("name"));
        String author = String.valueOf(req.getParameter("author"));
        int cost = Integer.valueOf(String.valueOf(req.getParameter("cost")));
        int languageId = Integer.valueOf(String.valueOf(req.getParameter(
                "languageId")));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        PublicationDAO publicationDAO = daoFactory.getPublicationDAO();
        Publication entity = new Publication(publicationId, author, name, cost,
                                            languageId);
        try {
            publicationDAO.update(entity);
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