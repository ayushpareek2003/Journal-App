package com.Pareek.journalApp.service;

import com.Pareek.journalApp.Entity.JournalEntry;
import com.Pareek.journalApp.repository.JournalEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class journalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public void save(JournalEntry journalEntry) {
        journalEntryRepo.save(journalEntry);
    }

}
