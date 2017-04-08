package app.entity;

import java.util.Date;

public class Task {
    private String id;
    private String name;
    private String description;
    private Date dateCreated;
    private boolean topPriority;
    private int estimation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (topPriority != task.topPriority) return false;
        if (estimation != task.estimation) return false;
        if (!id.equals(task.id)) return false;
        if (!name.equals(task.name)) return false;
        if (description != null ? !description.equals(task.description) : task.description != null) return false;
        return dateCreated.equals(task.dateCreated);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + dateCreated.hashCode();
        result = 31 * result + (topPriority ? 1 : 0);
        result = 31 * result + estimation;
        return result;
    }

    public Task() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isTopPriority() {
        return topPriority;
    }

    public void setTopPriority(boolean topPriority) {
        this.topPriority = topPriority;
    }

    public int getEstimation() {
        return estimation;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }
}
