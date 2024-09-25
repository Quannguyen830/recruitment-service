package com.example.recruitment_service.repository.mongo;

import com.example.recruitment_service.model.RequestResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestResponseRepository extends MongoRepository<RequestResponse, String> {

}
