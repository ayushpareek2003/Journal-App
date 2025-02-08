package com.Pareek.journalApp.repository;

import com.Pareek.journalApp.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


import java.util.List;
@Component
public class UserRepoImpl {

    @Autowired
    public MongoTemplate mongoTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


    public List<User> getUserWithEmail(){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").exists(true));

        List<User> users = mongoTemplate.find(query, User.class);
        return users;

    }





}
