package com.HomeTaskManager.HomeTaskManagerBackend.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//This import is not used but may be used
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class GroupController {
    @Autowired
    private GroupRepository groupRepository;

    @PostMapping(path="/addgroup")
    public @ResponseBody String addNewGroup (@RequestParam String name){
        Group n = new Group();
        n.setName(name);
        groupRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/allgroup")
    public @ResponseBody Iterable<Group> getAllGroups(){
        return groupRepository.findAll();
    }
}