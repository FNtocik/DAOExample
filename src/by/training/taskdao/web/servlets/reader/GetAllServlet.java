package by.training.taskdao.web.servlets.reader;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.ReaderDAO;
import by.training.taskdao.entities.Reader;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GetAllServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ReaderDAO readerDAO = factory.getReaderDAO();
        List<Reader> entities = null;
        try {
            entities = readerDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(entities != null){
            JSONArray jsonArray = new JSONArray();
            for (Reader current : entities) {
                JSONObject object = new JSONObject();
                object.append("readerId", current.getId());
                object.append("login", current.getLogin());
                object.append("password", current.getPassword());
                object.append("languageId", current.getLanguageId());
                jsonArray.put(object);
            }
            resp.getWriter().write(jsonArray.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
