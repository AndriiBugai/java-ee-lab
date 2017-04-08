package app;

import app.dao.TaskDao;
import app.dao.TaskDaoRDBMS;
import app.entity.Task;
import junit.framework.TestCase;
import org.junit.Test;
import org.postgresql.ds.PGPoolingDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.postgresql.jdbc2.optional.SimpleDataSource;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestTaskDaoRDBMS extends TestCase {
    TaskDao taskDao;
    Task newTask;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName("todo_list_db_test");
        dataSource.setUser("postgres");
        dataSource.setPassword("root");
        dataSource.setServerName("localhost");
        taskDao = new TaskDaoRDBMS(dataSource);

        newTask = new Task();
        newTask.setName("test Task To Add");
        newTask.setEstimation(1);
        newTask.setDescription("test description");
        newTask.setTopPriority(true);
        newTask.setDateCreated(new Date());
    }

    @Test
    public void testCreateTask() throws NamingException {
        List<Task> tasks = taskDao.getAll();
        taskDao.create(newTask);
        List<Task> updatedTasks = taskDao.getAll();
        Task createdTask = updatedTasks.get(updatedTasks.size() - 1);

        assertEquals(updatedTasks.size() - tasks.size(), 1);
        assertEquals(createdTask.getName(), newTask.getName());
        assertEquals(createdTask.getDescription(), newTask.getDescription());
        assertEquals(createdTask.getEstimation(), newTask.getEstimation());
        assertEquals(createdTask.isTopPriority(), newTask.isTopPriority());
    }

    @Test
    public void testDeleteTask() throws NamingException {
        List<Task> tasks = taskDao.getAll();
        taskDao.create(newTask);
        List<Task> updatedTasks = taskDao.getAll();
        Task createdTask = updatedTasks.get(updatedTasks.size() - 1);
        taskDao.delete(createdTask.getId());
        assertNull(taskDao.getEntityById(createdTask.getId()));
        assertEquals(updatedTasks.size() - taskDao.getAll().size(), 1);
    }

    @Test
    public void testUpdateTask() throws NamingException {
        List<Task> tasks = taskDao.getAll();
        taskDao.create(newTask);
        List<Task> updatedTasks = taskDao.getAll();
        Task createdTask = updatedTasks.get(updatedTasks.size() - 1);
        createdTask.setEstimation(10);
        taskDao.update(createdTask);
        assertEquals(createdTask, taskDao.getEntityById(createdTask.getId()));
    }

}
