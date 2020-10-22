package com.mitic.dalibor.controller;

import com.mitic.dalibor.model.User;
import com.mitic.dalibor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return this.userService.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<Boolean> registerUser(@RequestBody User userToReg) {
        User user = userService.findByUsername(userToReg.getUsername());
        if (user == null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(userToReg.getPassword());
            userToReg.setPassword(encodedPassword);
            userService.save(userToReg);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/users/login")
    public ResponseEntity<User> loginUser(@RequestBody User userFromReq) {
        User user = userService.findByUsername(userFromReq.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (user != null && encoder.matches(userFromReq.getPassword(), user.getPassword())) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}
