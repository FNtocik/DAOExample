package by.training.taskdao.web.servlets.reader;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.LanguageDAO;
import by.training.taskdao.dao.interfaces.ReaderDAO;
import by.training.taskdao.entities.Language;
import by.training.taskdao.entities.Reader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/secure/addReader")
public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ReaderDAO readerDAO = daoFactory.getReaderDAO();
        String login = String.valueOf(req.getParameter("login"));
        String password = String.valueOf(req.getParameter("password"));
        String languageParameter = req.getParameter("languageId");
        if (languageParameter.isEmpty()) {
            LanguageDAO languageDAO = daoFactory.getLanguageDAO();
            try {
                Language current = languageDAO.getAll().get(0);
                languageParameter = "" + current.getId();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        int languageId = Integer.valueOf(languageParameter);
        Reader entity = new Reader(login, password, languageId);
        try {
            readerDAO.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
