package by.training.task.web.servlets.language;

import by.training.task.dao.factory.DAOFactory;
import by.training.task.dao.interfaces.LanguageDAO;
import by.training.task.entities.Language;
import by.training.task.utils.LoggerManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet to edit language entity
 *
 * @author Anton Puhachou
 */
@WebServlet("/secure/editLanguage")
public class EditServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int languageId = Integer.valueOf(String.valueOf(req.getParameter(
                "languageId")));
        String signature = String.valueOf(req.getParameter("signature"));
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        LanguageDAO languageDAO = daoFactory.getLanguageDAO();
        Language entity = new Language(languageId, signature);
        try {
            languageDAO.update(entity);
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
