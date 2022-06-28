package com.back.ecomm.controller;

import com.back.ecomm.api.PetApi;

import com.back.ecomm.api.PetApiDelegate;
import com.back.ecomm.model.Pet;
import com.back.ecomm.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PetController implements  PetApi {

    private final PetApiDelegate delegate;
    private final PetService petService;

    @Autowired
    public PetController(@Autowired(required = false) PetApiDelegate delegate, PetService petService) {
        this.delegate = Optional.ofNullable(delegate).orElse(new PetApiDelegate() {});
        this.petService = petService;
    }

    @Override
    public PetApiDelegate getDelegate() {
        return delegate;
    }

    @Override
    public ResponseEntity<Pet> getPetById(Long petId) {
        return PetApi.super.getPetById(petId);
    }

    @Override
    public ResponseEntity<List<Pet>> findPetsByStatus(List<String> status) {
        List<Pet> pets = petService.getAllPets();
       return ResponseEntity.ok(pets);
    }
}
