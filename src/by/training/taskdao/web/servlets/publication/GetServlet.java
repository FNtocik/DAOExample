package by.training.taskdao.web.servlets.publication;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.PublicationDAO;
import by.training.taskdao.entities.Publication;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/secure/getPublication")
public class GetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        PublicationDAO publicationDAO = factory.getPublicationDAO();
        String parameterId = req.getParameter("publicationId");
        if (parameterId != null) {
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
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonObject.toString());
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
