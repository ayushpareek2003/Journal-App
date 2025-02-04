package com.Pareek.journalApp.service;

import com.Pareek.journalApp.Entity.User;
import com.Pareek.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class userService {

    @Autowired
    private UserRepo userRepo;

    public void saveUser(User user) {
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
