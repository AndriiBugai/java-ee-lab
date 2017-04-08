package app.dao;

import app.entity.Task;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoRDBMS implements TaskDao  {
    //String url = "jdbc:postgresql://localhost/todo_list_db?user=postgres&password=root";
    private DataSource ds;

    public TaskDaoRDBMS(DataSource datasource) throws NamingException {
        ds = datasource;
    }

    private Task parseTasksResultSet(ResultSet resultSet) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        int idIndex   = resultSet.findColumn("id");
        int nameIndex    = resultSet.findColumn("name");
        int descriptionIndex  = resultSet.findColumn("description");
        int dateCreatedIndex = resultSet.findColumn("date_created");
        int topPriorityIndex = resultSet.findColumn("top_priority");
        int estimationIndex = resultSet.findColumn("estimation");
        Task task = new Task();
        task.setId(resultSet.getString(idIndex));
        task.setName(resultSet.getString(nameIndex));
        task.setDescription(resultSet.getString(descriptionIndex));
        task.setDateCreated(resultSet.getDate(dateCreatedIndex));
        task.setTopPriority(resultSet.getBoolean(topPriorityIndex));
        task.setEstimation(resultSet.getInt(estimationIndex));
        return task;
    }

    @Override
    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();
        try(Connection connection = ds.getConnection()){
            String sql = "SELECT * FROM tasks";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                tasks.add(parseTasksResultSet(result));
            }
            connection.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return tasks;
    }

    @Override
    public Task getEntityById(String id) {
        Task task = null;
        try(Connection connection = ds.getConnection()){
            String sql = "SELECT * FROM tasks WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(id));
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                task = parseTasksResultSet(result);
            }
            connection.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);

        }
        return task;
    }

    @Override
    public Task update(Task entity) {
        Task task = new Task();
        try(Connection connection = ds.getConnection()){
            String sql = "UPDATE tasks\n" +
                         "SET name = ?, description=?, top_priority=?, estimation=? \n" +
                         "WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setBoolean(3, entity.isTopPriority());
            statement.setInt(4, entity.getEstimation());
            statement.setInt(5, Integer.valueOf(entity.getId()));

            ResultSet result = statement.executeQuery();
            if(result.next()) {
                task = parseTasksResultSet(result);
            }
            connection.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return task;
    }

    @Override
    public void delete(String id) {
        try(Connection connection = ds.getConnection()){
            String sql = "DELETE FROM tasks WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(id));
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Task create(Task entity) {
        try(Connection connection = ds.getConnection()){
            String sql = "INSERT INTO tasks\n" +
                    "(name, description, date_created, top_priority, estimation)\n" +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setTimestamp(3, new Timestamp(entity.getDateCreated().getTime()));
            statement.setBoolean(4, entity.isTopPriority());
            statement.setInt(5, entity.getEstimation());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return entity;
    }


    @Override
    public List<Task> getTopPriorityEntities() {
        return null;
    }

    @Override
    public List<Task> getEntitiesByKeyWord(String keyWord) {
        return null;
    }

    @Override
    public List<Task> getAllSortedByDate() {
        return null;
    }

    @Override
    public List<Task> getAllSortedByTopPriority() {
        return null;
    }
}
