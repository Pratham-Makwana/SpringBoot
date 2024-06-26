package com.pratham.journalApp.controller;

import com.pratham.journalApp.entity.JournalEntry;
import com.pratham.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("id/{id}")
    public JournalEntry getJournalEntriesById(@PathVariable ObjectId id){
        return journalEntryService.findById(id).orElse(null);
    }

    @DeleteMapping("id/{id}")
    public Boolean deleteJournalEntriesById(@PathVariable ObjectId id){
         journalEntryService.deleteById(id);
         return true;
    }

    @PutMapping("id/{id}")
    public JournalEntry updateJournalEntriesById(@PathVariable ObjectId id,@RequestBody JournalEntry newEntry){
            JournalEntry oldEntry = journalEntryService.findById(id).orElse(null);
            if (oldEntry != null){
                oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
                oldEntry.setContent(newEntry.getContent() != null && !newEntry.getTitle().equals("") ? newEntry.getContent() : oldEntry.getContent());
            }

            journalEntryService.saveEntry(oldEntry);
            return oldEntry;
    }



}
