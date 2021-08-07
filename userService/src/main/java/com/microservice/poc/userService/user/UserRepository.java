package com.microservice.poc.userService.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface UserRepository extends JpaRepository<User,String > {
    User findByEmailId(String emailId);
}
