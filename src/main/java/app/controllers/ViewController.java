package app.controllers;

import app.dao.TaskDao;
import app.dao.TaskDaoRDBMS;
import app.entity.Task;
import app.utils.Utils;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewController extends HttpServlet {
    private TaskDao taskDao;

    public void init() throws ServletException {
        try {
            taskDao = Utils.getTaskDao();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Task> tasks = taskDao.getAll();
        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
    }
}
