package com.Pareek.journalApp.Controller;

import com.Pareek.journalApp.Entity.JournalEntry;
import com.Pareek.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;


@RestController
@RequestMapping("/webjournal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private com.Pareek.journalApp.service.journalEntryService journalEntryService;


    @GetMapping("user/{username}")
    public ResponseEntity<?> getallJournalEntriesofuser(@PathVariable String username) {
        List<JournalEntry> temp= journalEntryService.getAllJournalEntrybyusername(username);
        if(!temp.isEmpty()){
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("addentry/{username}")
    @Transactional
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry,@PathVariable String username) {
        try {
            journalEntryService.saveEntry(entry,username);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("id/{myid}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId myid){
            Optional<JournalEntry> temp=journalEntryRepo.findById(myid);

            return temp.isPresent()? new ResponseEntity<>(temp.get(),HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{username}/{myid}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myid,@PathVariable String username){
        journalEntryService.deleteJournalEntrybyID(myid, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{myid}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId myid, @RequestBody JournalEntry entry){
        JournalEntry old=journalEntryRepo.findById(myid).orElse(null);
        if(old!=null){
            old.setTitle(entry.getTitle()!=null && !entry.getTitle().isEmpty()? entry.getTitle() : old.getTitle());
            old.setContent(entry.getContent()!=null && !entry.getContent().isEmpty()? entry.getContent() : old.getContent());
            old.setDate(entry.getDate());
            journalEntryRepo.save(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
