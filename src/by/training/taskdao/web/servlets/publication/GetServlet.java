package by.training.taskdao.web.servlets.publication;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.PublicationDAO;
import by.training.taskdao.entities.Publication;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class GetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int publicationId = Integer.valueOf(req.getParameter("publicationId"));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        PublicationDAO publicationDAO = daoFactory.getPublicationDAO();
        Publication entity = null;
        try {
            entity = publicationDAO.get(publicationId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject jsonAnswer = new JSONObject();
        jsonAnswer.append("publicationId", entity.getId());
        jsonAnswer.append("name", entity.getName());
        jsonAnswer.append("author", entity.getAuthor());
        jsonAnswer.append("cost", entity.getCost());
        jsonAnswer.append("languageId", entity.getLanguageId());
        resp.getWriter().write(jsonAnswer.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
