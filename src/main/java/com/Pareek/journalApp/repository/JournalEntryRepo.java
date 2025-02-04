package com.Pareek.journalApp.repository;


import com.Pareek.journalApp.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {



}
