package com.mitic.dalibor.service;

import com.mitic.dalibor.model.User;
import com.mitic.dalibor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsernameAndPassword(String username, String password){
        return this.userRepository.findByUsernameAndPassword(username, password);
    }

    public User findByUsername(String username){
        return this.userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public void save(User userToReg) {
        this.userRepository.save(userToReg);
    }

    public Optional<User> findById(Long id){
        return this.userRepository.findById(id);
    }
}
