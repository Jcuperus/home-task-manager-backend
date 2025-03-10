package com.HomeTaskManager.HomeTaskManagerBackend.user;

import com.HomeTaskManager.HomeTaskManagerBackend.common.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @GetMapping("/name/{username}")
    public @ResponseBody AppUser getUserByName(@PathVariable String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findUserByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        else {
            return user;
        }
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

    @PostMapping("/check")
    public ResponseEntity<Object> checkToken(@RequestBody String token){
        return MessageResponse.createSet("message", "Token is valid");
    }



}