package by.training.taskdao.web.servlets.publication;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.PublicationDAO;
import by.training.taskdao.entities.Publication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = String.valueOf(req.getParameter("name"));
        String author = String.valueOf(req.getParameter("author"));
        int cost = Integer.valueOf(String.valueOf(req.getParameter("cost")));
        int languageId = Integer.valueOf(String.valueOf(req.getParameter(
                                                    "languageId")));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        PublicationDAO publicationDAO = daoFactory.getPublicationDAO();
        Publication entity = new Publication(author, name, cost, languageId);
        try {
            publicationDAO.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
