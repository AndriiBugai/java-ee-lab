package app.dao;

import app.entity.Task;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class TaskDaoMongo implements TaskDao {

    MongoClient mongoClient;
    DB mongoToDoDB;
    DBCollection tasksCollection;
    final String tasksCollectionName = "tasks";

    public TaskDaoMongo() throws UnknownHostException {
        mongoClient = new MongoClient("localhost", 27017);
        mongoToDoDB = mongoClient.getDB("ToDoDB");
        tasksCollection = mongoToDoDB.getCollection(tasksCollectionName);
    }

    private Task parseTasksResultSet(DBObject mongoObject) {
        Task task = new Task();
        task.setId(String.valueOf(mongoObject.get("_id")));
        task.setName((String) mongoObject.get("name"));
        task.setDescription((String) mongoObject.get("description"));
        task.setDateCreated((Date) mongoObject.get("date_created"));
        task.setTopPriority((Boolean) mongoObject.get("top_priority"));
        task.setEstimation((int) mongoObject.get("estimation"));
        return task;
    }

    private void setEntityToDocument(Task entity, BasicDBObject document) {
        document.put("name", entity.getName());
        document.put("description", entity.getDescription());
        document.put("top_priority", entity.isTopPriority());
        document.put("date_created", entity.getDateCreated());
        document.put("estimation", entity.getEstimation());
    }

    @Override
    public List<Task> getAll() {
        BasicDBObject searchQuery = new BasicDBObject();
        DBCursor cursor = tasksCollection.find(searchQuery);
        List<Task> tasks = new ArrayList<>();
        while (cursor.hasNext()) {
            DBObject object = cursor.next();
            Task task = parseTasksResultSet(object);
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    public Task getEntityById(String id) {
        Task task = null;
        try {
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("_id", new ObjectId(id));
            DBCursor cursor = tasksCollection.find(searchQuery);
            if (cursor.hasNext()) {
                DBObject object = cursor.next();
                task = parseTasksResultSet(object);
            }
        } catch (MongoException e) {
            return null;
        }
        return task;
    }

    @Override
    public Task update(Task entity) {
        try {
            BasicDBObject document = new BasicDBObject();
            setEntityToDocument(entity, document);
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("_id", new ObjectId(entity.getId()));
            tasksCollection.update(searchQuery, document);
        } catch (MongoException e) {
            return null;
        }
        return entity;
    }

    @Override
    public void delete(String id) {
        try {
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("_id", new ObjectId(id));
            tasksCollection.remove(searchQuery);
        } catch (MongoException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Task create(Task entity) {
        try {
            BasicDBObject document = new BasicDBObject();
            setEntityToDocument(entity, document);
            tasksCollection.insert(document);
        } catch (MongoException e) {
            throw new IllegalArgumentException(e);
        }
        return entity;
    }

    @Override
    public List<Task> getTopPriorityEntities() {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("top_priority", true);
        DBCursor cursor = tasksCollection.find(searchQuery);
        List<Task> tasks = new ArrayList<>();
        while (cursor.hasNext()) {
            DBObject object = cursor.next();
            Task task = parseTasksResultSet(object);
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    public List<Task> getEntitiesByKeyWord(String keyWord) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", Pattern.compile(Pattern.quote(keyWord)));
        DBCursor cursor = tasksCollection.find(searchQuery);
        List<Task> tasks = new ArrayList<>();
        while (cursor.hasNext()) {
            DBObject object = cursor.next();
            Task task = parseTasksResultSet(object);
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    public List<Task> getAllSortedByDate() {
        BasicDBObject searchQuery = new BasicDBObject();
        BasicDBObject dateComparator = new BasicDBObject("date_created", 1);
        DBCursor cursor = tasksCollection.find(searchQuery).sort(dateComparator);
        List<Task> tasks = new ArrayList<>();
        while (cursor.hasNext()) {
            DBObject object = cursor.next();
            Task task = parseTasksResultSet(object);
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    public List<Task> getAllSortedByTopPriority() {
        BasicDBObject searchQuery = new BasicDBObject();
        BasicDBObject priorityComparator = new BasicDBObject("top_priority", -1);
        priorityComparator.put("date_created", 1);
        DBCursor cursor = tasksCollection.find(searchQuery).sort(priorityComparator);
        List<Task> tasks = new ArrayList<>();
        while (cursor.hasNext()) {
            DBObject object = cursor.next();
            Task task = parseTasksResultSet(object);
            tasks.add(task);
        }
        return tasks;
    }
}
