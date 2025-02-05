package com.Pareek.journalApp.Controller;

import com.Pareek.journalApp.Entity.JournalEntry;
import com.Pareek.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;


@RestController
@RequestMapping("webjournal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private com.Pareek.journalApp.service.journalEntryService journalEntryService;

//--------------------------- Already done use "user/entries"-----------------------------------
//    @GetMapping("user/{username}")
//    public ResponseEntity<?> getallJournalEntriesofuser(@PathVariable String username) {
//        List<JournalEntry> temp= journalEntryService.getAllJournalEntrybyusername(username);
//        if(!temp.isEmpty()){
//            return new ResponseEntity<>(temp, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//----------------------------------------------------------------------------------------------

    @PostMapping("addentry")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            journalEntryService.saveEntry(entry, auth.getName());
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
    }


//--------------------------------------------------------------------------------------------------------------------------------
//    @GetMapping("id/{myid}")
//    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId myid){
//            Optional<JournalEntry> temp=journalEntryRepo.findById(myid);
//
//            return temp.isPresent()? new ResponseEntity<>(temp.get(),HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//--------------------------------------------------------------------------------------------------------------------------------


    @DeleteMapping("id/{myid}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myid){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        journalEntryService.deleteJournalEntrybyID(myid, auth.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
