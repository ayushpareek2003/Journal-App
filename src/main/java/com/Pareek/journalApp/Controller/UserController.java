package com.Pareek.journalApp.Controller;

import com.Pareek.journalApp.Entity.User;
import com.Pareek.journalApp.repository.UserRepo;
import com.Pareek.journalApp.service.userService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private userService userservice;


    @GetMapping("")
    @RequestMapping
    public ResponseEntity<List<User>> getall(){
        List<User> temp=userservice.getAllUsers();

        if(temp.size()>0){
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @GetMapping("id/{userid}")
    public ResponseEntity<User> getuser(@PathVariable ObjectId id){
        Optional<User> temp=userservice.getUserById(id);
        if(temp.isPresent()){
            return new ResponseEntity<>(temp.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    @PostMapping("newuser")
    public ResponseEntity<User> adduser(@RequestBody User user){

        userservice.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateuser(@RequestBody User user){
        User temp=userservice.getUserByUsername(user.getUsername());
        if(temp!=null){
            temp.setUsername(user.getUsername());
            temp.setPassword(user.getPassword());
            userservice.saveUser(temp);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);



    }

//    @DeleteMapping
//    public ResponseEntity<?> deleteuser(@RequestBody User user){
//        User temp=userservice.getUserByUsername(user.getUsername());
//    }




}
