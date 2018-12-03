package by.training.task.web.servlets.login;

import by.training.task.dao.factory.DAOFactory;
import by.training.task.dao.interfaces.AdministratorDAO;
import by.training.task.dao.interfaces.ReaderDAO;
import by.training.task.entities.Administrator;
import by.training.task.entities.LoggedUser;
import by.training.task.entities.Reader;
import by.training.task.web.config.SecurityConfig;
import by.training.task.web.utils.SessionUtil;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ReaderDAO readerDAO = factory.getReaderDAO();
        AdministratorDAO administratorDAO = factory.getAdministratorDAO();
        List<Reader> readerList = null;
        List<Administrator> administratorList = null;
        try {
            readerList = readerDAO.getAll();
            administratorList = administratorDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LoggedUser user = null;
        if (readerList.size() != 0) {
            Reader tryToLog = new Reader(login, password, 0);
            if(readerList.contains(tryToLog))
                user = new LoggedUser(login, password, SecurityConfig.ROLE_READER);
        }
        if (user == null && administratorList.size() != 0) {
            Administrator tryToLog = new Administrator(login, password);
            if(administratorList.contains(tryToLog))
                user = new LoggedUser(login, password, SecurityConfig.ROLE_ADMINISTRATOR);
        }
        JSONObject answerJSON = new JSONObject();
        if(user != null) {
            SessionUtil.saveLoggedUserToSession(req.getSession(), user);
            answerJSON.append("answer", Boolean.TRUE);
        } else
            answerJSON.append("answer", Boolean.FALSE);
        resp.getWriter().write(answerJSON.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
