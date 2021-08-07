package com.microservice.poc.authenticationserver.service;

import com.microservice.poc.authenticationserver.Repo.UserRepository;
import com.microservice.poc.authenticationserver.model.AuthUserDetail;
import com.microservice.poc.authenticationserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByUserName(name);

        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));

        UserDetails userDetails = new AuthUserDetail(optionalUser.get());
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;


    }

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return  userRepository.findAll();
    }
}
