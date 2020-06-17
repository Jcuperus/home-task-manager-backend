package com.HomeTaskManager.HomeTaskManagerBackend.user;


import com.HomeTaskManager.HomeTaskManagerBackend.taskgroup.TaskGroup;
import com.HomeTaskManager.HomeTaskManagerBackend.tasks.Task;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class AppUser
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy="user")
    private List<Task> tasks;

    @ManyToMany(mappedBy = "users")
    private Set<TaskGroup> groups;

    private String username;

    private String password;

    public AppUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}