package com.Pareek.journalApp.Controller;


import com.Pareek.journalApp.Entity.User;
import com.Pareek.journalApp.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.ReturnedType;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private userService service;

    @GetMapping
    public ResponseEntity<?> getallusers() {
        List<User> allUsers = service.getAllUsers();

        if(allUsers.size() > 0 ){
            return new ResponseEntity<>(allUsers,HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("newAdmin/{name}")
    public ResponseEntity<?> newAdmin(@PathVariable String name) {
        User user=service.getUserByUsername(name);
        user.getRoles().add("ADMIN");
        service.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
