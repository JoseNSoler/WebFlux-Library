package com.example.library.mapper;

import com.example.library.dto.ResourceTextDTO;
import com.example.library.model.ResourceText;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Component
public class ResourceMapper {

    public ResourceText fromDTO(ResourceTextDTO dto) {
        ResourceText resource = new ResourceText();
        resource.setId(dto.getId());
        resource.setTitle(dto.getTitle());
        resource.setSubject(dto.getSubject());
        resource.setType(dto.getType());
        resource.setFormat(dto.getFormat());
        resource.setDescription(dto.getDescription());
        resource.setAvailable(dto.isAvailable());
        resource.setBorrowTime(dto.getBorrowTime());

        return resource;
    }


    public Function<ResourceText, ResourceTextDTO> mapDatoToDTO() {
        return entity -> new ResourceTextDTO(entity.getId(), entity.getTitle(), entity.getSubject(), entity.getType(), entity.getFormat(),
                entity.getDescription(), entity.isAvailable(), entity.getBorrowTime());
    }

    public ResourceTextDTO fromResource(ResourceText collection) {
        ResourceTextDTO empleadoDTO = new ResourceTextDTO();

        empleadoDTO.setId(collection.getId());
        empleadoDTO.setTitle(collection.getTitle());
        empleadoDTO.setSubject(collection.getSubject());
        empleadoDTO.setType(collection.getType());
        empleadoDTO.setFormat(collection.getFormat());
        empleadoDTO.setDescription(collection.getDescription());
        empleadoDTO.setAvailable(collection.isAvailable());
        empleadoDTO.setBorrowTime(collection.getBorrowTime());

        return empleadoDTO;
    }

    public Flux<ResourceTextDTO> fromCollectionList(Flux<ResourceText> collection) {
        if (collection == null) {
            return null;

        }
        return collection.map(resource -> new ResourceTextDTO(
                resource.getId(), resource.getTitle(), resource.getSubject(), resource.getType(), resource.getFormat(), resource.getDescription(),
                resource.isAvailable(), resource.getBorrowTime()
        ));
    }


}
