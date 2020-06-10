package com.HomeTaskManager.HomeTaskManagerBackend;

import com.HomeTaskManager.HomeTaskManagerBackend.models.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TaskController
{
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/task")
    public Task task() {
        return new Task(counter.incrementAndGet(), "Feed cat", "Give him the good stuff");  
    }
}
