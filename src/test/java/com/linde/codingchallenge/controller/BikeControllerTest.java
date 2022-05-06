package com.linde.codingchallenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linde.codingchallenge.entity.Bike;
import com.linde.codingchallenge.repository.BikeRepository;
import com.linde.codingchallenge.rest.BikeController;
import com.linde.codingchallenge.service.BikeServiceImpl;
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
public class BikeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    BikeRepository bikeRepository;

    @InjectMocks
    BikeController bikeController;

    @Autowired
    BikeServiceImpl bikeService;

    @Autowired
    ObjectMapper objectMapper;

    Bike BIKE_ONE = Bike.builder()
            .licenceNumber("7263DAS")
            .type("Mountain Bike")
            .color("Black")
            .ownerName("Alberto")
            .thiefDescription("Blue eyes, black shirt, blue jeans and red shoes")
            .stolenAddress("Calle Santa Rosa Lia")
            .stolenStatus(true)
            .build();
    Bike BIKE_TWO = Bike.builder()
            .licenceNumber("1148HFT")
            .type("Street")
            .color("Blue")
            .ownerName("Maria")
            .thiefDescription("Tall")
            .stolenAddress("Calle Santa Rosa Lia")
            .stolenStatus(true)
            .build();

    @Before("")
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bikeController).build();
    }

    @Test
    public void createBikeOKTest() throws Exception {
        Mockito.when(bikeRepository.save(BIKE_ONE)).thenReturn(BIKE_ONE);
        String content = objectMapper.writeValueAsString(BIKE_ONE);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/addBike")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.licenceNumber", is("7263DAS")));
    }

    
    @Test
    public void findAllBikesOKTest() throws Exception {

        List<Bike> bikes = new ArrayList<>(Arrays.asList(BIKE_ONE, BIKE_TWO));
        bikeService.createBike(BIKE_ONE);
        bikeService.createBike(BIKE_TWO);

        Mockito.when(bikeRepository.findAll()).thenReturn(bikes);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/bikes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].licenceNumber", is("7263DAS")));
    }

    
    @Test
    public void findByIdOKTest() throws Exception {

        bikeService.createBike(BIKE_ONE);

        Mockito.when(bikeRepository.findById(1L)).thenReturn(Optional.ofNullable(BIKE_ONE));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/bike/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.licenceNumber", is("7263DAS")));
    }

    
    @Test
    public void findByColorOKTest() throws Exception {

        List<Bike> bikes = new ArrayList<>(Arrays.asList(BIKE_ONE, BIKE_TWO));
        bikeService.createBike(BIKE_ONE);
        bikeService.createBike(BIKE_TWO);

        Mockito.when(bikeRepository.findBikesByColor("Black")).thenReturn(bikes);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/bike/color/black")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].color", is("Black")));
    }

    
    @Test
    public void findBikeByLicenceNumber() throws Exception {

        bikeService.createBike(BIKE_ONE);

        Mockito.when(bikeRepository.findBikeByLicenceNumber("7263DAS")).thenReturn(Optional.ofNullable(BIKE_ONE));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/bike/licence-number/7263DAS")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.licenceNumber", is("7263DAS")));
    }

    
    @Test
    public void findBikeByColorOrTypeOrStatus() throws Exception {

        List<Bike> bikes = new ArrayList<>(Arrays.asList(BIKE_ONE, BIKE_TWO));
        bikeService.createBike(BIKE_ONE);
        bikeService.createBike(BIKE_TWO);

        Mockito.when(bikeRepository.findBikesByColor("Black")).thenReturn(bikes);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/bikes/findByColorOrTypeOrStatus?color=Black&type=Street&status=true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].color", is("Black")))
                .andExpect(jsonPath("$[1].type", is("Street")));
    }

}
