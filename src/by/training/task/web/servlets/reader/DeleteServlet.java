package by.training.task.web.servlets.reader;

import by.training.task.dao.factory.DAOFactory;
import by.training.task.dao.interfaces.ReaderDAO;
import by.training.task.utils.LoggerManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet to delete reader entity
 *
 * @author Anton Puhachou
 */
@WebServlet("/secure/deleteReader")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int readerId =
                Integer.valueOf(String.valueOf(req.getParameter("readerId")));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ReaderDAO readerDAO = daoFactory.getReaderDAO();
        try {
            readerDAO.delete(readerId);
        } catch (SQLException e) {
            LoggerManager loggerManager = LoggerManager.getInstance();
            loggerManager.error(this.getClass().toString(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
