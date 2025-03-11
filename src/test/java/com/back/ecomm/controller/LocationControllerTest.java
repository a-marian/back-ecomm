package com.back.ecomm.controller;

import com.back.ecomm.entity.Country;
import com.back.ecomm.entity.State;
import com.back.ecomm.repository.CountryRepository;
import com.back.ecomm.repository.StateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LocationController.class)
class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryRepository countryRepository;

    @MockBean
    private StateRepository stateRepository;

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
        when(stateRepository.findByCountryCode(countryCode)).thenReturn(states);

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