package by.training.taskdao.web.servlets.publication;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.PublicationDAO;
import by.training.taskdao.entities.Publication;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PublicationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        PublicationDAO publicationDAO = factory.getPublicationDAO();
        String jsonAnswer = "";
        String parameterId = req.getParameter("publicationId");
        if(parameterId == null) {
            List<Publication> entities = null;
            try {
                entities = publicationDAO.getAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (entities != null) {
                JSONArray jsonArray = new JSONArray();
                for (Publication current : entities) {
                    JSONObject object = new JSONObject();
                    object.append("publicationId", current.getId());
                    object.append("name", current.getName());
                    object.append("author", current.getAuthor());
                    object.append("cost", current.getCost());
                    object.append("languageId", current.getLanguageId());
                    jsonArray.put(object);
                }
                jsonAnswer = jsonArray.toString();
            }
        }
        else {
            int publicationId = Integer.valueOf(parameterId);
            Publication entity = null;
            try {
                entity = publicationDAO.get(publicationId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.append("publicationId", entity.getId());
            jsonObject.append("name", entity.getName());
            jsonObject.append("author", entity.getAuthor());
            jsonObject.append("cost", entity.getCost());
            jsonObject.append("languageId", entity.getLanguageId());
            jsonAnswer = jsonObject.toString();
        }
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonAnswer);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int publicationId = Integer.valueOf(String.valueOf(req.getParameter(
                "publicationId")));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        PublicationDAO publicationDAO = daoFactory.getPublicationDAO();
        try {
            publicationDAO.delete(publicationId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
