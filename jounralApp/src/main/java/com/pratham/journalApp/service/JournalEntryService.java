package com.pratham.journalApp.service;

import com.pratham.journalApp.entity.JournalEntry;
import com.pratham.journalApp.entity.User;
import com.pratham.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    public void saveUserEntry(JournalEntry journalEntry, String useName) {
        User user = userService.findByUserName(useName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
       // adding the JournalEntry to the user JournalEntries list
        user.getJournalEntries().add(saved);
        // now save the user with new JournalEntries
        userService.saveUser(user);
    }
    public void saveEntry(JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        journalEntryRepository.save(journalEntry);

    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteByUsernameAndId(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(entry -> entry.getId().equals(id));
        userService.saveUser(user);
        journalEntryRepository.deleteById(id);


    }
    public void deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);

    }

}


// controller ---> service ---> repository
