package com.HomeTaskManager.HomeTaskManagerBackend.tasks;

import com.HomeTaskManager.HomeTaskManagerBackend.taskgroup.TaskGroup;
import com.HomeTaskManager.HomeTaskManagerBackend.user.AppUser;

import javax.persistence.*;

@Entity
public class Task
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="group_id")
    private TaskGroup taskGroup;

    @ManyToOne
    @JoinColumn(name="user_id")
    private AppUser user;

    private String name;

    private String description;

    private String dueDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TaskGroup getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(TaskGroup taskGroup) {
        this.taskGroup = taskGroup;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}
