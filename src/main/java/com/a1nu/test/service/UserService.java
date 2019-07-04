package com.a1nu.test.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.a1nu.test.entity.User;
import com.a1nu.test.repository.UserRepository;

@Transactional
@Service("userService")
public class UserService {
    private UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User getUserById(Integer id) {
        return this.userRepository.findUserById(id);
    }
    
    public User save(User user) {
        return userRepository.save(user);
    }
}
