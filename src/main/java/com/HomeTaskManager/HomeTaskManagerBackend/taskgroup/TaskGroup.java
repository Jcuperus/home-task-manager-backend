package com.HomeTaskManager.HomeTaskManagerBackend.taskgroup;

import com.HomeTaskManager.HomeTaskManagerBackend.tasks.Task;

import javax.persistence.*;
import java.util.List;

@Entity
public class TaskGroup
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy="taskGroup")
    private List<Task> tasks;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}