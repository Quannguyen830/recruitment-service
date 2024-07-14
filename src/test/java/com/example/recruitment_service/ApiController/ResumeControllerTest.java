package com.example.recruitment_service.ApiController;

import com.example.recruitment_service.common.response.ApiResponse;
import com.example.recruitment_service.dto.dtoIn.entity.LoginDtoIn;
import com.example.recruitment_service.dto.dtoIn.entity.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.entity.ResumeDtoIn;
import com.example.recruitment_service.dto.dtoOut.JobDtoOut;
import com.example.recruitment_service.dto.dtoOut.LoginDtoOut;
import com.example.recruitment_service.dto.dtoOut.PageDtoOut;
import com.example.recruitment_service.dto.dtoOut.ResumeDtoOut;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigInteger;

@SpringBootTest
@AutoConfigureMockMvc
public class ResumeControllerTest {
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
        pageDtoIn.setPageSize(10);

        var uri = UriComponentsBuilder.fromUriString("/resumes")
                .queryParam("page", pageDtoIn.getPage())
                .queryParam("pageSize", pageDtoIn.getPageSize())
                .toUriString();

        System.out.println("ACCESS TOKEN: " + accessToken);

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    var response = objectMapper.readValue(result.getResponse().getContentAsString(),
                            new TypeReference<ApiResponse<PageDtoOut<JobDtoOut>>>() {});
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

    @Test
    void get() throws Exception {
        BigInteger id = BigInteger.valueOf(8514215);

        String uri = UriComponentsBuilder.fromUriString("/resumes/{id}")
                .buildAndExpand(id)
                .toUriString();

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    var response = objectMapper.readValue(result.getResponse().getContentAsString(),
                            new TypeReference<ApiResponse<ResumeDtoOut>>() {
                            });
                    Assertions.assertNotNull(response);
                    Assertions.assertNotNull(response.getData());
                    Assertions.assertEquals(id, response.getData().getId());
                });
    }

    @Test
    void add() throws Exception {
        ResumeDtoIn resumeDtoIn = new ResumeDtoIn();
        resumeDtoIn.setTitle("Resume 1");
        resumeDtoIn.setSalary(100);
        resumeDtoIn.setCareerObj("Career Object");
        resumeDtoIn.setSeekerId(BigInteger.valueOf(4452654));
        resumeDtoIn.setFieldIds("-64-");
        resumeDtoIn.setProvinceIds(("-1-"));

        var uri = UriComponentsBuilder.fromUriString("/resumes")
                .toUriString();

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(resumeDtoIn)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(result -> {
                    var response = objectMapper.readValue(result.getResponse().getContentAsString(),
                            new TypeReference<ApiResponse<ResumeDtoOut>>() {
                            });
                    Assertions.assertNotNull(response);
                    Assertions.assertNotNull(response.getData());
                    Assertions.assertEquals(resumeDtoIn.getTitle(), response.getData().getTitle());
                });
    }
}
