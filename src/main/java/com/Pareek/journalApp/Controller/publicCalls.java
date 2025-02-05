package com.Pareek.journalApp.Controller;


import com.Pareek.journalApp.Entity.User;
import com.Pareek.journalApp.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("adduser")
public class publicCalls {

    @Autowired
    private userService service;
    @PostMapping("newuser")
    public ResponseEntity<User> adduser(@RequestBody User user){

        service.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
