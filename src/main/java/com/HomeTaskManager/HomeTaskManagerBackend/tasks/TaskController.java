package com.HomeTaskManager.HomeTaskManagerBackend.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TaskController
{
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks/{id}")
    public @ResponseBody Optional<Task> task(@PathVariable long id) {
        return taskRepository.findById(id);
    }

    @GetMapping("/tasks/all")
    public @ResponseBody Iterable<Task> tasks() {
        return taskRepository.findAll();
    }
}
