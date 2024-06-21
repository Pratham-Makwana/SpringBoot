package com.pratham.journalApp.service;

import com.pratham.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

}


// controller ---> service ---> repository
