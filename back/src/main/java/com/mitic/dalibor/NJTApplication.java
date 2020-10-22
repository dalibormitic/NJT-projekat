package com.mitic.dalibor;

import com.mitic.dalibor.model.User;
import com.mitic.dalibor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NJTApplication {

    public static void main(String[] args) {
        SpringApplication.run(NJTApplication.class, args);
    }

}
