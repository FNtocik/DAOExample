package by.training.task.web.servlets.language;

import by.training.task.dao.factory.DAOFactory;
import by.training.task.dao.interfaces.LanguageDAO;
import by.training.task.entities.Language;
import by.training.task.utils.LoggerManager;
import by.training.task.web.sort.enums.LanguageSortOrder;
import by.training.task.web.sort.util.LanguageSortUtil;
import by.training.task.web.utils.ListConfigUtil;
import by.training.task.web.utils.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/secure/getLanguage")
public class GetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        LanguageDAO languageDAO = factory.getLanguageDAO();
        String counterParam = req.getParameter("counter");
        String numberParam = req.getParameter("number");
        LanguageSortOrder sortOrderFromSession = (LanguageSortOrder) SessionUtil.getSortOrderFromSession(req.getSession());
        int counter;
        int number;
        String parameterIndex = req.getParameter("languageIndex");
        if (parameterIndex != null) {
            int languageIndex = Integer.valueOf(parameterIndex);
            Language entity = null;
            List<Language> entities = null;
            try {
                entities = languageDAO.getAll();
            } catch (SQLException e) {
                LoggerManager loggerManager = LoggerManager.getInstance();
                loggerManager.error(this.getClass().toString(), e);
            }
            if (entities != null) {
                if (sortOrderFromSession != null) {
                    entities = LanguageSortUtil.sort(entities, sortOrderFromSession);
                }
                if (entities.size() != 0) {
                    if (counterParam == null || numberParam == null) {
                        counter = 0;
                        number = entities.size();
                    } else {
                        counter = Integer.valueOf(counterParam);
                        number = Integer.valueOf(numberParam);
                    }
                    entities = ListConfigUtil.getPartOfList(entities, counter, number);
                    entity = entities.get(languageIndex);
                    if (entity != null) {
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(entity.toString());
                    }
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
