package com.pratham.journalApp.repository;

import com.pratham.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository  extends MongoRepository<User, ObjectId> {

    User findByUserName(String username);
}
