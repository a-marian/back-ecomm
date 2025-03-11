package com.back.ecomm.controller;

import com.back.ecomm.entity.Country;
import com.back.ecomm.entity.State;
import com.back.ecomm.repository.CountryRepository;
import com.back.ecomm.repository.StateRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LocationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private StateRepository stateRepository;

    @InjectMocks
    private LocationController locationController;
    
    @BeforeEach
    void setUp(){
    
        mockMvc = MockMvcBuilders.standaloneSetup(locationController).build();
    }

    @Test
    void getCountries_shouldReturnListOfCountries() throws Exception {
        // Arrange
        List<Country> countries = Arrays.asList(new Country("US", "United States"), new Country("CA", "Canada"));
        when(countryRepository.findAll()).thenReturn(countries);

        // Act and Assert
        mockMvc.perform(get("/api/countries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].code", is("US")))
                .andExpect(jsonPath("$[0].name", is("United States")))
                .andExpect(jsonPath("$[1].code", is("CA")))
                .andExpect(jsonPath("$[1].name", is("Canada")));
    }

    @Test
    void getStatesByCountry_shouldReturnListOfStates() throws Exception {
        // Arrange
        String countryCode = "US";
   
        List<State> states = Arrays.asList(new State("NY", "New York"), new State("CA", "California"));
        when(stateRepository.findByCountryCode(anyString())).thenReturn(states);

        // Act and Assert
        mockMvc.perform(get("/api/states/country/{countryCode}", countryCode))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].code", is("NY")))
                .andExpect(jsonPath("$[0].name", is("New York")))
                .andExpect(jsonPath("$[1].code", is("CA")))
                .andExpect(jsonPath("$[1].name", is("California")));
    }
}