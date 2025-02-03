package com.Pareek.journalApp.Controller;

import com.Pareek.journalApp.Entity.JournalEntry;
import com.Pareek.journalApp.repository.JournalEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/webjournal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryRepo journalEntryRepo;



    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry entry){
        journalEntryRepo.save(entry);
        return true;


    }

    @GetMapping("id/{myid}")
    public JournalEntry getEntryById(@PathVariable String myid){
    return null;

    }

    @DeleteMapping("id/{myid}")
    public JournalEntry deleteEntryById(@PathVariable String myid){

    return null;
    }

    @PutMapping("id/{myid}")
    public void updateEntryById(@PathVariable Long myid, @RequestBody JournalEntry entry){

    }

}
