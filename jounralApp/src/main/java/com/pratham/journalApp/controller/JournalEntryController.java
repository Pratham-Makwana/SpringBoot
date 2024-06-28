package com.pratham.journalApp.controller;

import com.pratham.journalApp.entity.JournalEntry;
import com.pratham.journalApp.entity.User;
import com.pratham.journalApp.service.JournalEntryService;
import com.pratham.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;


    //  Get All Journal Entries
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<JournalEntry> all = journalEntryService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get All Journal Entries Of The Particular User
    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOFUser(@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Create a New Entry For the JournalEntry in a journal_entries collection
    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry) {
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Creating new Journal Entries For The Particular User
    @PostMapping("/{userName}")
    public ResponseEntity<?> creatingUserEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {
        try {
            journalEntryService.saveUserEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//
//    @GetMapping("id/{id}")
//    public JournalEntry getJournalEntriesById(@PathVariable ObjectId id){
//        return journalEntryService.findById(id).orElse(null);
//    }

    // Handling HttpStatus Code
    // Get The JournalEntry by its Id
    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntriesById(@PathVariable ObjectId id) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

//        if (journalEntry.isPresent()){
//            return  new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Only Delete The JournalEntry By Id
    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteJournalEntriesById(@PathVariable ObjectId id) {
        journalEntryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Delete JournalEntries from Journal Collection As well as User Collection JournalEntry List
    @DeleteMapping("id/{userName}/{id}")
    public ResponseEntity<?> deleteUserJournalEntries(@PathVariable ObjectId id,@PathVariable String userName) {
        journalEntryService.deleteByUsernameAndId(id,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // Update Journal Entries Using ID
    @PutMapping("id/{id}")
    public ResponseEntity<?> updateJournalEntriesById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        JournalEntry oldEntry = journalEntryService.findById(id).orElse(null);
        if (oldEntry != null) {
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getTitle().equals("") ? newEntry.getContent() : oldEntry.getContent());
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("id/{userName}/{id}")
    public ResponseEntity<?> updateUserJournalEntries(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry,@PathVariable String userName) {
        JournalEntry oldEntry = journalEntryService.findById(id).orElse(null);
        if (oldEntry != null) {
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getTitle().equals("") ? newEntry.getContent() : oldEntry.getContent());
            journalEntryService.saveUserEntry(oldEntry,userName);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
