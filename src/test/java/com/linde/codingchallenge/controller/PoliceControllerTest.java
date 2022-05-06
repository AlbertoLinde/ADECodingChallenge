package com.linde.codingchallenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linde.codingchallenge.entity.Police;
import com.linde.codingchallenge.repository.PoliceRepository;
import com.linde.codingchallenge.rest.PoliceController;
import com.linde.codingchallenge.service.PoliceDepartmentServiceImpl;
import com.linde.codingchallenge.service.PoliceServiceImpl;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@NoArgsConstructor
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PoliceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PoliceServiceImpl policeService;
    @Autowired
    PoliceDepartmentServiceImpl policeDepartmentService;

    @Autowired
    ObjectMapper objectMapper;

    @Mock
    PoliceRepository policeRepository;

    @InjectMocks
    PoliceController policeController;

    Police POLICE_ONE = Police.builder()
            .name("Alberto")
            .investigating(true)
            .build();
    Police POLICE_TWO = Police.builder()
            .name("Marta")
            .investigating(true)
            .build();

    @Before("")
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(policeController).build();
    }

    @Test
    public void createPoliceOKTest() throws Exception {
        Mockito.when(policeRepository.save(POLICE_ONE)).thenReturn(POLICE_ONE);
        String content = objectMapper.writeValueAsString(POLICE_ONE);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/add-police")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Alberto")));
    }


    @Test
    public void findByIdOKTest() throws Exception {

        policeService.createPolice(POLICE_ONE);

        Mockito.when(policeRepository.findById(1L)).thenReturn(Optional.ofNullable(POLICE_ONE));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/police/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Alberto")));
    }


    @Test
    public void findAllPoliceOfficersOKTest() throws Exception {

        List<Police> polices = new ArrayList<>(Arrays.asList(POLICE_ONE, POLICE_TWO));
        policeService.createPolice(POLICE_ONE);
        policeService.createPolice(POLICE_TWO);

        Mockito.when(policeRepository.findAll()).thenReturn(polices);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/police-officers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Alberto")))
                .andExpect(jsonPath("$[1].name", is("Marta")));
    }

}
