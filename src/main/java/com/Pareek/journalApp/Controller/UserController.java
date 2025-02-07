package com.Pareek.journalApp.Controller;

import ch.qos.logback.core.joran.sanity.Pair;
import com.Pareek.journalApp.Entity.JournalEntry;
import com.Pareek.journalApp.Entity.User;
import com.Pareek.journalApp.service.userService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {


    @Autowired
    private userService userservice;

//----------------------------------------------------------------------------
//    @GetMapping("")
//    public ResponseEntity<List<User>> getall(){
//        List<User> temp=userservice.getAllUsers();
//
//        if(temp.size()>0){
//            return new ResponseEntity<>(temp, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }
//-----------------------------------------------------------------------------






    @GetMapping("entries")
    public ResponseEntity<?> getUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User temp=userservice.getUserByUsername(auth.getName());
        List<JournalEntry> journalEntries=temp.getEntries();

        return new ResponseEntity<>(journalEntries, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateuser(@RequestBody User user){

        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        User temp=userservice.getUserByUsername(auth.getName());
        temp.setUsername(user.getUsername());
        temp.setPassword(user.getPassword());
        userservice.saveUser(temp);
        return new ResponseEntity<>(null, HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity deleteuser(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        User temp=userservice.getUserByUsername(auth.getName());
        userservice.deleteUserById(temp.getId());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("total")
    public ResponseEntity<?> getUserTotal(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        User temp=userservice.getUserByUsername(auth.getName());
        return new ResponseEntity<>(temp.getEntries().toArray().length, HttpStatus.OK);
    }

}
