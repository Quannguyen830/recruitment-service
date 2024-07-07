package com.example.recruitment_service.ApiController;

import com.example.recruitment_service.dto.request.entity.EmployerDtoIn;
import com.example.recruitment_service.dto.request.entity.PageDtoIn;
import com.example.recruitment_service.dto.request.updateEntity.UpdatedEmployerDtoIn;
import com.example.recruitment_service.dto.response.EmployerDtoOut;
import com.example.recruitment_service.dto.response.PageDtoOut;
import com.example.recruitment_service.controller.EmployerController;
import com.example.recruitment_service.service.EmployerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployerController.class)
public class EmployerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployerService employerService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAddEmployer() throws Exception {
        EmployerDtoIn employerDtoIn = new EmployerDtoIn();
        // Set up the employerDtoIn object as needed

        Mockito.when(employerService.createEmployer(any(EmployerDtoIn.class))).thenReturn(any());

        mockMvc.perform(post("/employers/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employerDtoIn)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetAllEmployers() throws Exception {
        PageDtoOut<EmployerDtoOut> pageDtoOut = new PageDtoOut<>();
        Mockito.when(employerService.getAllEmployers(any(PageDtoIn.class))).thenReturn(pageDtoOut);

        mockMvc.perform(get("/employers/")
                        .param("page", "1")
                        .param("pageSize", "10"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteEmployer() throws Exception {
        Mockito.doNothing().when(employerService).deleteEmployer(anyLong());

        mockMvc.perform(delete("/employers/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetEmployerById() throws Exception {
        Mockito.when(employerService.getEmployerById(anyLong())).thenReturn(any());

        mockMvc.perform(get("/employers/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateEmployer() throws Exception {
        UpdatedEmployerDtoIn updatedEmployerDtoIn = new UpdatedEmployerDtoIn();
        // Set up the updatedEmployerDtoIn object as needed

        Mockito.doNothing().when(employerService).updateEmployer(anyLong(), any(UpdatedEmployerDtoIn.class));

        mockMvc.perform(put("/employers/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedEmployerDtoIn)))
                .andExpect(status().isNoContent());
    }
}
