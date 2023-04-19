package com.back.ecomm.controller;

import com.back.ecomm.entity.Country;
import com.back.ecomm.entity.State;
import com.back.ecomm.repository.CountryRepository;
import com.back.ecomm.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class LocationController {

    private CountryRepository countryRepository;
    private StateRepository stateRepository;
    LocationController(@Autowired CountryRepository countryRepository,
                       @Autowired StateRepository stateRepository){
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
    }

    @GetMapping("countries")
    public ResponseEntity<List<Country>> getCountries(){
        List<Country> countries = countryRepository.findAll();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping("states/country/{countryCode}")
    public ResponseEntity<List<State>> getStatesByCountry(@PathVariable String countryCode){
        List<State> states = stateRepository.findByCountryCode(countryCode);
        return new ResponseEntity<>(states, HttpStatus.OK);
    }
}
