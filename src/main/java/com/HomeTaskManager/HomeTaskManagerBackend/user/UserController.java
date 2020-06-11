package com.HomeTaskManager.HomeTaskManagerBackend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//This import is not used but may be used
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller


public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/users/add")
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String password, 
    @RequestParam String email, @RequestParam String role) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/users/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
}