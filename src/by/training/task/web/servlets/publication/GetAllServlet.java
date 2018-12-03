package by.training.task.web.servlets.publication;

import by.training.task.dao.factory.DAOFactory;
import by.training.task.dao.interfaces.PublicationDAO;
import by.training.task.entities.Publication;
import org.json.JSONArray;

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
        String currentLocale = String.valueOf(req.getAttribute("locale"));
        List<Publication> entities = null;
        try {
            entities = publicationDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (entities != null && entities.size() != 0) {
            for (int i = 0; i < entities.size(); i++) {
                if (!entities.get(i).getLanguage().getSignature().equals(currentLocale)) {
                    entities.remove(i);
                    i--;
                }
            }
            JSONArray jsonArray = new JSONArray();
            for (Publication current : entities) {
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
