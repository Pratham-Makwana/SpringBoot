package com.pratham.journalApp.repository;

import com.pratham.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
}

// MongoRepository<JournalEntry,String> first parameter where you what to perform operation and second type of id
