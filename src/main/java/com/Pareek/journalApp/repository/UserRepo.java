package com.Pareek.journalApp.repository;

import com.Pareek.journalApp.Entity.JournalEntry;
import com.Pareek.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepo extends MongoRepository<User, ObjectId>{
    User findByUsername(String username);
}
