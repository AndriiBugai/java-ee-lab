package app.dao;

import app.entity.Task;
import java.util.List;

public interface TaskDao extends AbstractDao <Task, String>  {
    public List<Task> getTopPriorityEntities();
    public List<Task> getEntitiesByKeyWord(String keyWord);
    public List<Task> getAllSortedByDate();
    public List<Task> getAllSortedByTopPriority();
}
