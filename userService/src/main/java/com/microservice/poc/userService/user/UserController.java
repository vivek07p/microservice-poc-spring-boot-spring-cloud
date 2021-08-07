package com.microservice.poc.userService.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    private BCryptPasswordEncoder passwordEncoder;
    private UserService userService;

    public UserController(BCryptPasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("sign-up")
    public void signUp(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
    }
    @GetMapping("user-list")
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }

    @GetMapping("get/{emailId}")
    public User getUser(@PathVariable("emailId") String emailId){
        return userService.getByEmailId(emailId);
    }



}
