package com.HomeTaskManager.HomeTaskManagerBackend.tasks;

import com.HomeTaskManager.HomeTaskManagerBackend.common.MessageResponse;
import com.HomeTaskManager.HomeTaskManagerBackend.taskgroup.TaskGroup;
import com.HomeTaskManager.HomeTaskManagerBackend.taskgroup.TaskGroupRepository;
import com.HomeTaskManager.HomeTaskManagerBackend.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController
{
    private final TaskRepository taskRepository;
    private final TaskGroupRepository groupRepository;
    private final UserRepository userRepository;

    public TaskController(TaskRepository taskRepository, TaskGroupRepository groupRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public @ResponseBody Optional<Task> task(@PathVariable long id) {
        return taskRepository.findById(id);
    }

    @GetMapping("")
    public @ResponseBody Iterable<Task> tasks(@RequestParam(name="group", required=false) Long[] groups, Principal principal) {
        if (groups != null && groups.length > 0) {
            return taskRepository.findAllByTaskGroup_IdInOrderByDueDate(groups);
        }

        // Get user groups
        List<TaskGroup> userGroups = groupRepository.findAllByUsers_Username(principal.getName());
        Long[] groupIds = new Long[userGroups.size()];

        for (int i = 0; i < userGroups.size(); i++) {
            groupIds[i] = userGroups.get(i).getId();
        }

        return taskRepository.findAllByTaskGroup_IdInOrderByDueDate(groupIds);
    }

    @PostMapping("")
    public ResponseEntity<Object> createTask(@RequestBody Task task, Principal principal) {
        try {
            task.setUser(userRepository.findUserByUsername(principal.getName()));
            taskRepository.save(task);
            return MessageResponse.createSet("message", String.format("Created task with id=%s", task.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed creating task");
        }
    }

    @PutMapping("")
    public ResponseEntity<Object> updateTask(@RequestBody Task task) {
        if (taskRepository.existsById(task.getId())) {
            taskRepository.save(task);
            return MessageResponse.createSet("message", String.format("Updated task with id=%s", task.getId()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Updated task does not exist");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable long id) {
        taskRepository.deleteById(id);
        return MessageResponse.createSet("message", String.format("Deleted task with id=%s", id));
    }
}
