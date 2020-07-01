package com.HomeTaskManager.HomeTaskManagerBackend.taskgroup;

import com.HomeTaskManager.HomeTaskManagerBackend.tasks.Task;
import com.HomeTaskManager.HomeTaskManagerBackend.user.AppUser;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class TaskGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "taskGroup")
    private Set<Task> tasks;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "task_group_app_user", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<AppUser> users;

    private String name;

    private String color;

    public long getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public void setUsers(Set<AppUser> users) {
        this.users = users;
    }

    public void addUser(AppUser user){
        users.add(user);
    }

    public void removeUserByName(String username){
        users.removeIf(user -> user.getUsername().equals(username));
    }

    public Set<AppUser> getUsers(){
        return users;
    }
}