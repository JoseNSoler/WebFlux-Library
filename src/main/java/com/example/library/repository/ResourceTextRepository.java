package com.example.library.repository;

import com.example.library.model.ResourceText;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ResourceTextRepository extends ReactiveMongoRepository<ResourceText, String> {
}
