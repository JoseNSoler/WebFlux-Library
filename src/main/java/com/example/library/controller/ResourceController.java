package com.example.library.controller;

import com.example.library.dto.ResourceTextDTO;
import com.example.library.service.ResourceTextServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
public class ResourceController {

    private final ResourceTextServiceImpl resourceTextService;

    public ResourceController(ResourceTextServiceImpl resourceTextService) {
        this.resourceTextService = resourceTextService;
    }

    public Mono<ServerResponse> findAll(ServerRequest req) {
        return ok().body(this.resourceTextService.findAll(), ResourceTextDTO.class);
    }

    public Mono<ServerResponse> recommendBySubject(ServerRequest req) {
        return ok().body(this.resourceTextService.recommendBySubject(String.valueOf(req.pathVariable("subject")))
                , ResourceTextDTO.class);
    }

    public Mono<ServerResponse> recommendByType(ServerRequest req) {
        return ok().body(this.resourceTextService.recommendByType(String.valueOf(req.pathVariable("type")))
                , ResourceTextDTO.class);
    }


    public Mono<ServerResponse> getById(ServerRequest req) {
        return this.resourceTextService.getById(String.valueOf(req.pathVariable("id")))
                .flatMap(post -> ok().syncBody(post))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> save(ServerRequest req) {
        return req.body(BodyExtractors.toMono(ResourceTextDTO.class))
                .flatMap(post -> this.resourceTextService.save(post))
                .flatMap(p -> created(URI.create("/posts/" + p.getId())).build());
    }

    public Mono<ServerResponse> reserveByID(ServerRequest req) {
        return this.resourceTextService.reserveByID(String.valueOf(req.pathVariable("id")))
                .flatMap(post -> ok().body(post, ResourceTextDTO.class))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> returnByID(ServerRequest req) {
        return this.resourceTextService.returnByID(String.valueOf(req.pathVariable("id")))
                .flatMap(post -> ok().body(post, ResourceTextDTO.class))
                .switchIfEmpty(notFound().build());
    }








/*
    @PostMapping("/save")
    public ResponseEntity<ResourceTextDTO> save(@RequestBody ResourceTextDTO resourceTextDTO){
        return new ResponseEntity(resourceTextService.save(resourceTextDTO), HttpStatus.CREATED);
    }



    @DeleteMapping("/deleteByID/{id}")
    public ResponseEntity<ResourceTextDTO> deleteById(@PathVariable("id") String id){
        return new ResponseEntity(resourceTextService.deleteById(id), HttpStatus.CREATED);
    }




    @PutMapping("/reserveResource/{id}")
    public ResponseEntity<String>  reserveResource(@PathVariable("id") String id){
        return resourceTextService.reserveResource(id);
    }

    @PutMapping("/returnResource/{id}")
    public ResponseEntity<String> returnResource(@PathVariable("id") String id){
        return resourceTextService.returnResource(id);
    }


    @GetMapping("/getRecommended/query")
    public List<ResourceTextDTO> recommendedResource(
            @RequestParam("type") Optional<String> type,
            @RequestParam("subject") Optional<String> subject
    ){
        if(type.isPresent() && subject.isPresent())
            return resourceTextService.recommendedResource(type.get(), subject.get());
        if(type.isPresent() && !(subject.isPresent()))
            return resourceTextService.recommendedResource(type.get(), null);
        if(!(type.isPresent()) && subject.isPresent()){
            return resourceTextService.recommendedResource(null, subject.get());
        }

        return null;*/
}
