package com.Pareek.journalApp.service;

import com.Pareek.journalApp.Entity.User;
import com.Pareek.journalApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class userDetailsServiceimpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
//        String username2 = auth.getName();

        if(username==null){
            throw new UsernameNotFoundException("Username not found");
        }

        User user = userRepo.findByUsername(username);

        return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
    }
}
