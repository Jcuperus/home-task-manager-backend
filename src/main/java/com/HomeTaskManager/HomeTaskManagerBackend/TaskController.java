package com.HomeTaskManager.HomeTaskManagerBackend;

import com.HomeTaskManager.HomeTaskManagerBackend.models.Task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping(path="/task")

@RestController
public class TaskController
{
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/task")
    public Task task() {
        return new Task(counter.incrementAndGet(), "Feed cat", "Give him the good stuff");
    }
}
