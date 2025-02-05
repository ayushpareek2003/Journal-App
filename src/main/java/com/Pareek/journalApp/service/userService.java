package com.Pareek.journalApp.service;

import com.Pareek.journalApp.Entity.User;
import com.Pareek.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class userService {

    @Autowired
    private UserRepo userRepo;

    private static final PasswordEncoder passenc=new BCryptPasswordEncoder();

    public void saveUser(User user) {
        user.setPassword(passenc.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepo.save(user);

    }

    public void updateUser(User user) {
        userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getUserById(ObjectId id) {
        if(userRepo.findById(id).isPresent()) {
            return userRepo.findById(id);
        }
        return null;
    }

    public void deleteUserById(ObjectId id) {
        userRepo.deleteById(id);
    }

    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }


}
