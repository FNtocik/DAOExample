package by.training.taskdao.web.servlets.reader;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.ReaderDAO;
import by.training.taskdao.entities.Reader;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/secure/getReader")
public class GetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ReaderDAO readerDAO = factory.getReaderDAO();
        String parameterId = req.getParameter("readerId");
        if (parameterId != null) {
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
