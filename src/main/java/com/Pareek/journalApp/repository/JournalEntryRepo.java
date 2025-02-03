package com.Pareek.journalApp.repository;


import com.Pareek.journalApp.Entity.JournalEntry;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;


public interface JournalEntryRepo extends MongoRepository<JournalEntry, String> {


}
