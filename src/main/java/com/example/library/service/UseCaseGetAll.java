package com.example.library.service;

import com.example.library.dto.ResourceTextDTO;
import com.example.library.mapper.ResourceMapper;
import com.example.library.repository.ResourceTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;


@Service
@Validated
public class UseCaseGetAll implements Supplier<Flux<ResourceTextDTO>> {
    private final ResourceTextRepository textRepository;
    private final ResourceMapper resourceMapper;

    @Autowired
    public UseCaseGetAll(ResourceMapper resourceMapper, ResourceTextRepository textRepository) {
        this.textRepository = textRepository;
        this.resourceMapper = resourceMapper;
    }

    @Override
    public Flux<ResourceTextDTO> get() {
        return textRepository.findAll().map(resourceMapper.mapDatoToDTO());
    }
}