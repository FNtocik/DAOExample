package by.training.task.web.servlets.reader;

import by.training.task.dao.factory.DAOFactory;
import by.training.task.dao.interfaces.ReaderDAO;
import by.training.task.entities.Reader;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/secure/getAllReader")
public class GetAllServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String counterParam = req.getParameter("counter");
        String numberParam = req.getParameter("number");
        int counter;
        int number;
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ReaderDAO readerDAO = factory.getReaderDAO();
        List<Reader> entities = null;
        try {
            entities = readerDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (entities != null && entities.size() != 0) {
            if (counterParam == null || numberParam == null) {
                counter = 0;
                number = entities.size();
            } else {
                counter = Integer.valueOf(counterParam);
                number = Integer.valueOf(numberParam);
            }
            if (counter >= entities.size()) return;
            if (counter + number >= entities.size())
                entities = entities.subList(counter, entities.size());
            else
                entities = entities.subList(counter, counter + number);
            JSONArray jsonArray = new JSONArray();
            for (Reader current : entities) {
                jsonArray.put(current.toString());
            }
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonArray.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}