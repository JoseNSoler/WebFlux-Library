package com.example.library.controller;

import com.example.library.dto.ResourceTextDTO;
import com.example.library.repository.ResourceTextRepository;
import com.example.library.service.ResourceTextServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class ResourceTextControllerTest {

    @MockBean
    private ResourceTextServiceImpl resourceTextService;

    @MockBean
    private ResourceTextRepository resourceTextRepository;

    @Autowired
    private MockMvc mockMvc;



    @Test
    @DisplayName("POST save")
    void save() {

    }
}