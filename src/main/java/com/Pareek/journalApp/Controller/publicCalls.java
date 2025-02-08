package com.Pareek.journalApp.Controller;


import com.Pareek.journalApp.Entity.User;
import com.Pareek.journalApp.service.JwtUtil;
import com.Pareek.journalApp.service.userDetailsServiceimpl;
import com.Pareek.journalApp.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("adduser")
public class publicCalls {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private userService service;

    @Autowired
    private userDetailsServiceimpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("signup")
    public ResponseEntity<User> adduser(@RequestBody User user){

        service.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody User user){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);

        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        }


    }





}
