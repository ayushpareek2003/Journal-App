package com.Pareek.journalApp.Controller;


import com.Pareek.journalApp.Entity.User;
import com.Pareek.journalApp.repository.UserRepoImpl;
import com.Pareek.journalApp.service.EmailService;
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

    @Autowired
    private  EmailService emailService;



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

    @GetMapping("emailuser")
    public ResponseEntity<?> getemailuser() {
            return new ResponseEntity<>(service.userswithEmail(),HttpStatus.OK);
    }

    @GetMapping("sendEmail")
    public ResponseEntity<?> sendemail() {
        List<User> allUsers = service.userswithEmail();
        for(int l=0;l<100;l++) {
            for (User user : allUsers) {
                String toy = "Uncle kha hoo?";
                String subject = "Testing";
                String email = user.getEmail();
                emailService.sendEmail(email, toy, subject);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
