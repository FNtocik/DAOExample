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

public class ReaderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ReaderDAO readerDAO = factory.getReaderDAO();
        String jsonAnswer = "";
        String parameterId = req.getParameter("readerId");
        if(parameterId == null) {
            List<Reader> entities = null;
            try {
                entities = readerDAO.getAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (entities != null) {
                JSONArray jsonArray = new JSONArray();
                for (Reader current : entities) {
                    JSONObject object = new JSONObject();
                    object.append("readerId", current.getId());
                    object.append("login", current.getLogin());
                    object.append("password", current.getPassword());
                    object.append("languageId", current.getLanguageId());
                    jsonArray.put(object);
                }
                jsonAnswer = jsonArray.toString();
            }
        }
        else{
            int readerId = Integer.valueOf(parameterId);
            Reader entity = null;
            try {
                entity = readerDAO.get(readerId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.append("readerId", entity.getId());
            jsonObject.append("login", entity.getLogin());
            jsonObject.append("password", entity.getPassword());
            jsonObject.append("languageId", entity.getLanguageId());
            jsonAnswer = jsonObject.toString();
        }
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonAnswer);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int readerId = Integer.valueOf(String.valueOf(req.getParameter(
                "readerId")));
        String login = String.valueOf(req.getParameter("login"));
        String password = String.valueOf(req.getParameter("password"));
        int languageId = Integer.valueOf(String.valueOf(req.getParameter(
                "languageId")));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ReaderDAO readerDAO = daoFactory.getReaderDAO();
        Reader entity = new Reader(readerId, login, password, languageId);
        try {
            readerDAO.update(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = String.valueOf(req.getParameter("login"));
        String password = String.valueOf(req.getParameter("password"));
        int languageId = Integer.valueOf(String.valueOf(req.getParameter(
                "languageId")));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ReaderDAO readerDAO = daoFactory.getReaderDAO();
        Reader entity = new Reader(login, password, languageId);
        try {
            readerDAO.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int readerId =
                Integer.valueOf(String.valueOf(req.getParameter("readerId")));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ReaderDAO readerDAO = daoFactory.getReaderDAO();
        try {
            readerDAO.delete(readerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
