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
import java.util.Date;

public class CreateTaskController extends HttpServlet {
    private TaskDao taskDao;

    public void init() throws ServletException {
        try {
            taskDao = Utils.getTaskDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Date date = new Date();
            boolean topPriority = Boolean.valueOf(request.getParameter("top_priority"));
            int estimation = Integer.valueOf(request.getParameter("estimation"));

            Task task = new Task();
            task.setName(name);
            task.setDescription(description);
            task.setDateCreated(date);
            task.setTopPriority(topPriority);
            task.setEstimation(estimation);
            taskDao.create(task);

            response.sendRedirect("/to-do-list");
    }
}
