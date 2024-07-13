package com.example.recruitment_service.ApiController;

import com.example.recruitment_service.controller.EmployerController;
import com.example.recruitment_service.dto.dtoIn.entity.EmployerDtoIn;
import com.example.recruitment_service.dto.dtoIn.entity.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.updateEntity.UpdatedEmployerDtoIn;
import com.example.recruitment_service.dto.dtoOut.EmployerDtoOut;
import com.example.recruitment_service.service.impl.BaseRedisServiceImpl;
import com.example.recruitment_service.service.impl.EmployerServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployerController.class)
@ExtendWith(MockitoExtension.class)
public class EmployerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployerServiceImpl employerService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddEmployer() throws Exception {
        EmployerDtoIn employerDtoIn = new EmployerDtoIn();
        employerDtoIn.setEmail("test@example.com");
        EmployerDtoOut employerDtoOut = new EmployerDtoOut();

        when(employerService.createEmployer(any(EmployerDtoIn.class))).thenReturn(employerDtoOut);

        mockMvc.perform(post("/employers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employerDtoIn)))
                .andExpect(status().isCreated());

        verify(employerService, times(1)).createEmployer(any(EmployerDtoIn.class));
    }

    @Test
    void testGetAllEmployers() throws Exception {
        PageDtoIn pageDtoIn = new PageDtoIn();

        mockMvc.perform(get("/employers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "1")
                        .param("pageSize", "10"))
                .andExpect(status().isOk());

        verify(employerService, times(1)).getAllEmployers(any(PageDtoIn.class));
    }

    @Test
    void testDeleteEmployer() throws Exception {
        long id = 1L;

        mockMvc.perform(delete("/employers/{id}", id))
                .andExpect(status().isNoContent());

        verify(employerService, times(1)).deleteEmployer(id);
    }

    @Test
    void testGetEmployerById() throws Exception {
        long id = 1L;
        EmployerDtoOut employerDtoOut = new EmployerDtoOut();

        when(employerService.getEmployerById(id)).thenReturn(employerDtoOut);

        mockMvc.perform(get("/employers/{id}", id))
                .andExpect(status().isOk());

        verify(employerService, times(1)).getEmployerById(id);
    }

    @Test
    void testUpdateEmployer() throws Exception {
        long id = 1L;
        UpdatedEmployerDtoIn updatedEmployerDtoIn = new UpdatedEmployerDtoIn();

        mockMvc.perform(put("/employers/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedEmployerDtoIn)))
                .andExpect(status().isNoContent());

        verify(employerService, times(1)).updateEmployer(eq(id), any(UpdatedEmployerDtoIn.class));
    }
}
