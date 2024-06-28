package com.pratham.journalApp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "user")
@Data
public class User {

    @Id
    private ObjectId id;
    // when it's creating the user collection by default,
    // indexed unique is not unique we have to do manually in mongoDb,
    // but we're using the spring, so we added in the application.properties
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String password;

    @DBRef // It is referencing to the journalEntry, like in sql called as a foreign key.
    private List<JournalEntry> journalEntries = new ArrayList<>();

}
