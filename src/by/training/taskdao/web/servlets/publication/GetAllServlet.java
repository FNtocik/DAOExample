package by.training.taskdao.web.servlets.publication;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.PublicationDAO;
import by.training.taskdao.entities.Publication;
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

@WebServlet("/secure/getAllPublication")
public class GetAllServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        PublicationDAO publicationDAO = factory.getPublicationDAO();
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
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonArray.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
