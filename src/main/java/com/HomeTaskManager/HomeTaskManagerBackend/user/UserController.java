package com.HomeTaskManager.HomeTaskManagerBackend.user;

import com.HomeTaskManager.HomeTaskManagerBackend.common.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController
{
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/current")
    public ResponseEntity<AppUser> currentUser(Principal principal) {
        AppUser currentUser = userRepository.findUserByUsername(principal.getName());
        return ResponseEntity.ok(currentUser);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> signUp(@RequestBody AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return MessageResponse.createSet("message", String.format("User %s created", user.getUsername()));
    }



}