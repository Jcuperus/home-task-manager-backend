package com.HomeTaskManager.HomeTaskManagerBackend.tasks;

import com.HomeTaskManager.HomeTaskManagerBackend.user.AppUser;
import com.HomeTaskManager.HomeTaskManagerBackend.user.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController
{
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskController(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public @ResponseBody Optional<Task> task(@PathVariable long id) {
        return taskRepository.findById(id);
    }

    @GetMapping("")
    public @ResponseBody Iterable<Task> tasks(@RequestParam(name="group", required=false) Long[] groups) {
        if (groups != null && groups.length > 0) {
            return taskRepository.findAllByTaskGroup_IdIn(groups);
        }

        return taskRepository.findAll();
    }

    @PostMapping("")
    public @ResponseBody String createTask(@RequestBody Task task, Principal principal) {
        try {
            task.setUser(userRepository.findUserByUsername(principal.getName()));
            taskRepository.save(task);
            return String.format("Created task id=%s", task.getId());
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
