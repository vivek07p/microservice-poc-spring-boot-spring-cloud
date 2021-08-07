package com.microservice.poc.userService.user;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
 public class UserService  {
    private  UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    void save(User user){
        userRepository.save(user);
     }

    List<User> getAllUsers() {
        return  userRepository.findAll();
    }

    User getByEmailId(String emailId){
        return userRepository.getOne(emailId);
    }
}
