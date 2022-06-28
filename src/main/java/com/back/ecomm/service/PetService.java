package com.back.ecomm.service;

import com.back.ecomm.model.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PetService {

    List<Pet> findPetByStatus(List<String> status);

    Pet getPet(Long id);

    List<Pet> getAllPets();

    Pet save(Pet pet );
}
