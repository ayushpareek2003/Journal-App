package com.Pareek.journalApp.Controller;

import com.Pareek.journalApp.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<ObjectId, JournalEntry> Entries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getall(){
        return new ArrayList<>(Entries.values());
    }



    @GetMapping("id/{myid}")
    public JournalEntry getEntryById(@PathVariable ObjectId myid){
        return Entries.get(myid);
    }

    @DeleteMapping("id/{myid}")
    public JournalEntry deleteEntryById(@PathVariable ObjectId myid){

        JournalEntry entry = Entries.get(myid);
        Entries.remove(myid);
        return entry;
    }

    @PutMapping("id/{myid}")
    public void updateEntryById(@PathVariable ObjectId myid, @RequestBody JournalEntry entry){
        Entries.put(myid, entry);
    }

}
