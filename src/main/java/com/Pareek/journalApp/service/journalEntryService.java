package com.Pareek.journalApp.service;
import com.Pareek.journalApp.Entity.JournalEntry;
import com.Pareek.journalApp.repository.JournalEntryRepo;
import com.Pareek.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.Pareek.journalApp.Entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class journalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private userService userservice;


    public void saveEntry(JournalEntry journalEntry,String name) {
        try {
            journalEntry.setDate(LocalDateTime.now());
            User temp = userservice.getUserByUsername(name);
            JournalEntry temp_saved = journalEntryRepo.save(journalEntry);
            temp.getEntries().add(temp_saved);
            userservice.updateUser(temp);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<JournalEntry> getAllJournalEntrybyusername(String username) {
        User temp_user=userservice.getUserByUsername(username);
        return temp_user.getEntries();

    }

    public void deleteJournalEntrybyID( ObjectId myid,String username) {
        User temp_user=userservice.getUserByUsername(username);
        temp_user.getEntries().removeIf(x->x.getId().equals(myid));
        userservice.saveUser(temp_user);
        journalEntryRepo.deleteById(myid);
    }

    public void updateJournalEntrybyID( ObjectId myid,JournalEntry journalEntry) {
        JournalEntry temp=journalEntryRepo.findById(myid).get();
        temp.setDate(LocalDateTime.now());
        temp.setTitle(journalEntry.getTitle());
        temp.setContent(journalEntry.getContent());
        journalEntryRepo.save(temp);
    }


}
