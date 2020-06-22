package com.HomeTaskManager.HomeTaskManagerBackend.taskgroup;

import com.HomeTaskManager.HomeTaskManagerBackend.common.MessageResponse;
import com.HomeTaskManager.HomeTaskManagerBackend.user.AppUser;
import com.HomeTaskManager.HomeTaskManagerBackend.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/groups")
public class TaskGroupController
{
    private final TaskGroupRepository taskGroupRepository;
    private final UserRepository userRepository;

    public TaskGroupController(TaskGroupRepository taskGroupRepository, UserRepository userRepository) {
        this.taskGroupRepository = taskGroupRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("{id}")
    public @ResponseBody
    Optional<TaskGroup> taskGroup(@PathVariable long id) {
        return taskGroupRepository.findById(id);
    }

    @GetMapping("")
    public @ResponseBody Iterable<TaskGroup> taskGroups(Principal principal) {
        return taskGroupRepository.findAllByUsers_Username(principal.getName());
    }

    @PostMapping("")
    public ResponseEntity<Object> createTaskGroup(@RequestBody TaskGroup taskGroup, Principal principal) {
        return MessageResponse.createSet("message", String.format("Task group %s created", "test"));
    }
}