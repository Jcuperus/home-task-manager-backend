package com.HomeTaskManager.HomeTaskManagerBackend.taskgroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//This import is not used but may be used
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class TaskGroupController
{
    @Autowired
    private TaskGroupRepository taskGroupRepository;

    @PostMapping(path="/addgroup")
    public @ResponseBody String addNewGroup (@RequestParam String name){
        TaskGroup n = new TaskGroup();
        n.setName(name);
        taskGroupRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<TaskGroup> getAllUsers(){
        return taskGroupRepository.findAll();
    }
}