package by.training.taskdao.servlets.reader;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.PaymentDAO;
import by.training.taskdao.dao.interfaces.ReaderDAO;
import by.training.taskdao.entities.Payment;
import by.training.taskdao.entities.Reader;
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
        int readerId = Integer.valueOf(req.getParameter("readerId"));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ReaderDAO readerDAO = daoFactory.getReaderDAO();
        Reader entity = null;
        try {
            entity = readerDAO.get(readerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject jsonAnswer = new JSONObject();
        jsonAnswer.append("readerId", entity.getId());
        jsonAnswer.append("login", entity.getLogin());
        jsonAnswer.append("password", entity.getPassword());
        jsonAnswer.append("languageId", entity.getLanguageId());
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonAnswer.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
