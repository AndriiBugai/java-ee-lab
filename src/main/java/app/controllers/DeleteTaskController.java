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

public class DeleteTaskController extends HttpServlet {
    private TaskDao taskDao;

    public void init() throws ServletException {
        try {
            taskDao = Utils.getTaskDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String id = request.getParameter("id");
            boolean isDeleteSuccessful = taskDao.delete(id);
            response.sendRedirect("/to-do-list");
    }
}
