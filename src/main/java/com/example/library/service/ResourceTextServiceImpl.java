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




/*

    @Override
    public ResponseEntity<String> reserveResource(String id) {
        return resourceTextRepository.findById(id).map((resource) -> {
            System.out.println(resource);
            if(resource.isAvailable()) {
                resource.setAvailable(false);
                resource.setBorrowTime(new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z").format(new Date()));
                resourceTextRepository.save(resource);
                return new ResponseEntity("Se presto el recurso" + resource, HttpStatus.ACCEPTED);
                //new ResponseEntity(resource + " fue prestado", HttpStatus.ACCEPTED);
            };
           // new ResponseEntity(resource + " no puede ser prestado", HttpStatus.ACCEPTED);
            return new ResponseEntity("NO SE PRESTO EL RECURSO, YA ESTA PRESTADO!!" + resource, HttpStatus.ACCEPTED);

        }).get();
    }

    @Override
    public ResponseEntity<String> returnResource(String id) {
        return resourceTextRepository.findById(id).map((resource) -> {
            System.out.println(resource);
            if(!resource.isAvailable()) {
                resource.setAvailable(true);
                resource.setBorrowTime(new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z").format(new Date()));
                resourceTextRepository.save(resource);
                return new ResponseEntity("Se a devuelto el recurso exitosamente" + resource, HttpStatus.ACCEPTED);
                //new ResponseEntity(resource + " fue prestado", HttpStatus.ACCEPTED);
            };
            // new ResponseEntity(resource + " no puede ser prestado", HttpStatus.ACCEPTED);
            return new ResponseEntity("NO SE PUEDE DEVOLVER EL RECURSO, ESTE NO ESTA PRESTADO!!" + resource, HttpStatus.ACCEPTED);

        }).get();
    }

    public static boolean checkArray( Object arrayOrString, String queryType) {
        boolean condition =  arrayOrString.getClass().toString().equals("class java.util.ArrayList");
        if(condition){
            ArrayList list = new ArrayList<>((Collection<?>) arrayOrString);
            Object newList = list.stream().filter(element -> (element.toString().contains(queryType)))
                    .collect(Collectors.toCollection(ArrayList::new));
            ArrayList listFilter = new ArrayList<>((Collection<?>) newList);
            return (!listFilter.isEmpty());
        }
        if(!condition){
            return (String.valueOf(arrayOrString).contains(queryType));
        }
        return false;
    }

    public static boolean checkSubject(ResourceText subject, String querySubject){
        String subjectList =  subject.getSubject().toString();
        return( StringUtils.contains(subjectList.toLowerCase(), querySubject.toLowerCase()) );
    }



    @Override
    public List<ResourceTextDTO> recommendedResource(String typeText, String subject){
        var result = resourceTextRepository.findAll();

        if(typeText != null && subject != null){
            return resourceMapper.fromCollectionList(
                    result.stream()
                    .filter(resourceText -> resourceText.getSubject() != null && resourceText.getFormat() != null)
                    .filter(resourceText -> (
                            (checkSubject(resourceText, subject) && (checkArray(resourceText.getFormat(), typeText)) )
                    ))
                    .collect(Collectors.toList())
            );
        }

        if(typeText == null && subject != null){
            return resourceMapper.fromCollectionList(
                    result.stream()
                    .filter(resourceText -> resourceText.getSubject() != null)
                    .filter(resourceText -> ( (checkSubject(resourceText, subject)) ))
                    .collect(Collectors.toList())
            );
        }
        if(typeText != null && subject == null){
            return resourceMapper.fromCollectionList(
                    result.stream()
                    .filter(resourceText -> resourceText.getFormat() != null)
                    .filter(resourceText -> (checkArray(resourceText.getFormat(), typeText)))
                    .collect(Collectors.toList())
            );
        }
        ResourceTextDTO resourceText = new ResourceTextDTO();
        resourceText.setDescription("No exister recurso con los parametros solicitados");
        return List.of(resourceText);
    }





    @Override
    public ResourceTextDTO deleteById(String id) {
        ResourceText barry = resourceTextRepository.findById(id).get();
        if(!barry.getId().isEmpty())
            resourceTextRepository.deleteById(id);

        return resourceMapper.fromResource(
                barry
        );
    }
*/

}
