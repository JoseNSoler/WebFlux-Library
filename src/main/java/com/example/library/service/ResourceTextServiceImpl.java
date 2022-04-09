package com.example.library.service;


import com.example.library.dto.ResourceTextDTO;
import com.example.library.mapper.ResourceMapper;
import com.example.library.model.ResourceText;
import com.example.library.repository.ResourceTextRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;


@Service
public class ResourceTextServiceImpl implements IResourceTextService {

    @Autowired
    private ResourceTextRepository resourceTextRepository;

    private final BiFunction<ResourceTextRepository, ResourceText, Mono<ResourceText>> validateBeforeInsert
            = (repo, person) -> repo.findById(person.getId());

    ResourceMapper resourceMapper = new ResourceMapper();


    @Override
    public Flux<ResourceTextDTO> findAll() {
        return resourceMapper.fromCollectionList(resourceTextRepository.findAll());
    }

    @Override
    public Mono<ResourceTextDTO> getById(String id) {
        return resourceTextRepository.findById(id).map(resourceText ->
             resourceMapper.fromResource(resourceText)
        );
    }

    @Override
    public Mono<ResourceTextDTO> save(ResourceTextDTO resourceTextDTO) {
        return resourceTextRepository.save(resourceMapper.fromDTO(resourceTextDTO))
                        .map(resourceText -> resourceMapper.fromResource(resourceText));
    }



    @Override
    public Mono<Object> reserveByID(String id) {
        return resourceTextRepository.findById(id).map((resource) -> {
            System.out.println(resource);
            if(resource.isAvailable()) {
                resource.setAvailable(false);
                resource.setBorrowTime(new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z").format(new Date()));
                return resourceTextRepository.save(resource);
                //new ResponseEntity(resource + " fue prestado", HttpStatus.ACCEPTED);
            };
            // new ResponseEntity(resource + " no puede ser prestado", HttpStatus.ACCEPTED);
            return Mono.just(new ResourceTextDTO());
        });
    }

    @Override
    public Mono<Object> returnByID(String id) {
        return resourceTextRepository.findById(id).map((resource) -> {
            System.out.println(resource);
            if(!resource.isAvailable()) {
                resource.setAvailable(true);
                resource.setBorrowTime(new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z").format(new Date()));
                return resourceTextRepository.save(resource);
                //new ResponseEntity(resource + " fue prestado", HttpStatus.ACCEPTED);
            };
            // new ResponseEntity(resource + " no puede ser prestado", HttpStatus.ACCEPTED);
            return Mono.just(new ResourceTextDTO());
        });
    }

    public static boolean checkArray( Object arrayOrString, String queryType) {
        boolean condition = arrayOrString.getClass().toString().equals("class java.util.ArrayList");
        if (condition) {
            ArrayList list = new ArrayList<>((Collection<?>) arrayOrString);
            Object newList = list.stream().filter(element -> (element.toString().contains(queryType)))
                    .collect(Collectors.toCollection(ArrayList::new));
            ArrayList listFilter = new ArrayList<>((Collection<?>) newList);
            return (!listFilter.isEmpty());
        }
        if (!condition) {
            return (String.valueOf(arrayOrString).contains(queryType));
        }
        return false;
    }

    @Override
    public Flux<ResourceTextDTO> recommendByType(String typeText){
        var result = resourceTextRepository.findAll();

        if(typeText != null){
            return resourceMapper.fromCollectionList(
                    result.filter(resourceText -> resourceText.getFormat() != null)
                            .filter(resourceText -> (checkArray(resourceText.getFormat(), typeText)))
            );
        }
        ResourceTextDTO resourceText = new ResourceTextDTO();
        resourceText.setDescription("No exister recurso con los parametros solicitados");
        return Flux.just(resourceText);
    }


    public static boolean checkSubject(ResourceText subject, String querySubject){
        String subjectList =  subject.getSubject().toString();
        return( StringUtils.contains(subjectList.toLowerCase(), querySubject.toLowerCase()) );
    }



    @Override
    public Flux<ResourceTextDTO> recommendBySubject(String subject){
        var result = resourceTextRepository.findAll();

        if(subject != null){
            return resourceMapper.fromCollectionList(
                    result.filter(resourceText -> resourceText.getSubject() != null)
                            .filter(resourceText -> ( (checkSubject(resourceText, subject)) ))
            );
        }
        ResourceTextDTO resourceText = new ResourceTextDTO();
        resourceText.setDescription("No exister recurso con los parametros solicitados");
        return Flux.just(resourceText);
    }


}
