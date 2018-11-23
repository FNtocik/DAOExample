package by.training.taskdao.web.servlets.reader;

import by.training.taskdao.dao.factory.DAOFactory;
import by.training.taskdao.dao.interfaces.ReaderDAO;
import by.training.taskdao.entities.Reader;

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
            if (entity != null) {
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(entity.toString());
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
