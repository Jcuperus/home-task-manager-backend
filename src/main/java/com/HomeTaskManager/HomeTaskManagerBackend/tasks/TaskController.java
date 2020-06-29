package com.HomeTaskManager.HomeTaskManagerBackend.tasks;

import com.HomeTaskManager.HomeTaskManagerBackend.common.MessageResponse;
import com.HomeTaskManager.HomeTaskManagerBackend.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
            return taskRepository.findAllByTaskGroup_IdInOrderByDueDate(groups);
        }

        return taskRepository.findAll();
    }

    @PostMapping("")
    public ResponseEntity<Object> createTask(@RequestBody Task task, Principal principal) {
        try {
            task.setUser(userRepository.findUserByUsername(principal.getName()));
            task.setIsDone(false);
            taskRepository.save(task);
            return MessageResponse.createSet("message", String.format("Created task with id=%s", task.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed creating task");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> finishTask(@PathVariable Long id){
        taskRepository.findById(id).get().setIsDone(true);
        return MessageResponse.createSet("message", String.format("finished task with id=%d", taskRepository.findById(id).get()));
    }

    @PutMapping("")
    public ResponseEntity<Object> updateTask(@RequestBody Task task) {
        if (taskRepository.existsById(task.getId())) {
            taskRepository.save(task);
            return MessageResponse.createSet("message", String.format("Updated task with id=%s", task.getId()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Updated task does not exist");
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable long id) {
        taskRepository.deleteById(id);
        return MessageResponse.createSet("message", String.format("Deleted task with id=%s", id));
    }
}
