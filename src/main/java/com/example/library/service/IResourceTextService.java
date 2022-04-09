package com.example.library.service;

import com.example.library.dto.ResourceTextDTO;
import com.example.library.model.ResourceText;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

public interface IResourceTextService {
    Flux<ResourceTextDTO> findAll();
    Mono<ResourceTextDTO> getById(String id);
    Mono<ResourceTextDTO> save(ResourceTextDTO resourceTextDTO);
    Mono<Object> reserveByID(String id);
    Mono<Object> returnByID(String id);
    Flux<ResourceTextDTO> recommendByType(String typeText);
    Flux<ResourceTextDTO> recommendBySubject(String subject);
/*
    ResponseEntity<String>  reserveResource(String id);
    ResponseEntity<String> returnResource(String id);
    List<ResourceTextDTO> recommendedResource(String type, String subject);

    ResourceTextDTO save(ResourceTextDTO resourceTextDTO);
    ResourceTextDTO getById(String id);
    ResourceTextDTO deleteById(String id);
*/

}
