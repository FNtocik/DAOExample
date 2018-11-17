package by.training.taskdao.web.servlets.reader;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.ReaderDAO;
import by.training.taskdao.entities.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
