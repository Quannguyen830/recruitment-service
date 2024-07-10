package com.example.recruitment_service.ApiController;

import com.example.recruitment_service.dto.dtoIn.entity.PageDtoIn;
import com.example.recruitment_service.dto.dtoOut.JobDtoOut;
import com.example.recruitment_service.dto.dtoOut.PageDtoOut;
import com.fasterxml.jackson.core.type.TypeReference;
import com.example.recruitment_service.common.response.ApiResponse;
import com.example.recruitment_service.controller.JobController;
import com.example.recruitment_service.dto.dtoIn.entity.LoginDtoIn;

import com.example.recruitment_service.dto.dtoOut.LoginDtoOut;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.UriComponentsBuilder;

@WebMvcTest(controllers = JobController.class)
@AutoConfigureMockMvc
public class JobControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String accessToken;

    @BeforeEach
    void getAccessToken() throws Exception {
        var uri = UriComponentsBuilder.fromUriString("/auth/login").toUriString();

        LoginDtoIn loginDtoIn = new LoginDtoIn();
        loginDtoIn.setUsername("quan");
        loginDtoIn.setPassword("password");

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(loginDtoIn)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(result -> {
                    var response = objectMapper.readValue(result.getResponse().getContentAsString(),
                            new TypeReference<ApiResponse<LoginDtoOut>>() {
                            });
                    accessToken = response.getData().getToken();
                });
    }

    @Test
    void list() throws Exception {
        PageDtoIn pageDtoIn = new PageDtoIn();
        pageDtoIn.setPage(2);
        pageDtoIn.setPageSize(100);

        var uri = UriComponentsBuilder.fromUriString("/users").queryParam("page", pageDtoIn.getPage())
                .queryParam("pageSize", pageDtoIn.getPageSize()).toUriString();

        System.out.println("ACCESS TOKEN: " + accessToken);

        mockMvc.perform(MockMvcRequestBuilders.get(uri).header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    var response = objectMapper.readValue(result.getResponse().getContentAsString(),
                            new TypeReference<ApiResponse<PageDtoOut<JobDtoOut>>>() {
                            });
                    Assertions.assertNotNull(response);
                    Assertions.assertNotNull(response.getData());
                    Assertions.assertNotNull(response.getData().getData());
                    long totalElements = response.getData().getTotalElements();
                    long totalPages = response.getData().getTotalPages();

                    long expectedTotalPages = totalElements / pageDtoIn.getPageSize();
                    if (totalElements % pageDtoIn.getPageSize() != 0) {
                        expectedTotalPages += 1;
                    }

                    Assertions.assertEquals(expectedTotalPages, totalPages);

                    long expectedDataSize = pageDtoIn.getPageSize();

                    if (pageDtoIn.getPage() > totalPages) {
                        expectedDataSize = 0L;
                    }
                    if (pageDtoIn.getPage() < totalPages) {
                        expectedDataSize = pageDtoIn.getPageSize();
                    }

                    if ((long) pageDtoIn.getPage() == totalPages) {
                        if (totalElements % pageDtoIn.getPageSize() == 0) {
                            expectedDataSize = pageDtoIn.getPageSize();
                        } else {
                            expectedDataSize = totalElements % pageDtoIn.getPageSize();
                        }
                    }

                    Assertions.assertEquals(expectedDataSize, response.getData().getData().size());
                });
    }
}
