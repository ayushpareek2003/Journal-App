package com.Pareek.journalApp.Controller;

import com.Pareek.journalApp.Entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<Long, JournalEntry> Entries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getall(){
        return new ArrayList<>(Entries.values());
    }

    @PostMapping
    public void createEntry(@RequestBody JournalEntry entry){

        Entries.put(entry.getId(), entry);

    }

    @GetMapping("id/{myid}")
    public JournalEntry getEntryById(@PathVariable long myid){
        return Entries.get(myid);
    }

    @DeleteMapping("id/{myid}")
    public JournalEntry deleteEntryById(@PathVariable Long myid){

        JournalEntry entry = Entries.get(myid);
        Entries.remove(myid);
        return entry;
    }

    @PutMapping("id/{myid}")
    public void updateEntryById(@PathVariable Long myid, @RequestBody JournalEntry entry){
        Entries.put(myid, entry);
    }

}
