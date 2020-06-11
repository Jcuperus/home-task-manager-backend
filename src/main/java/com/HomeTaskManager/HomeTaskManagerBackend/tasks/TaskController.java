package com.HomeTaskManager.HomeTaskManagerBackend.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController
{
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/{id}")
    public @ResponseBody Optional<Task> task(@PathVariable long id) {
        return taskRepository.findById(id);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Task> tasks() {
        return taskRepository.findAll();
    }

    @PostMapping("")
    public @ResponseBody String createTask(@RequestBody Task task) {
        try {
            Task createdTask = taskRepository.save(task);
            return String.format("Created task id=%s %s", createdTask.getId(), task.getId());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PutMapping("")
    public @ResponseBody String updateTask(@RequestBody Task task) {
        if (taskRepository.existsById(task.getId())) {
            taskRepository.save(task);
            return String.format("Updated task %s", task.getId());
        }

        return "nope.avi";
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String deleteTask(@PathVariable long id) {
        taskRepository.deleteById(id);

        return String.format("Deleted %s", id);
    }
}
