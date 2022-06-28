package com.back.ecomm.service;

import com.back.ecomm.entity.Category;
import com.back.ecomm.entity.PetEntity;
import com.back.ecomm.model.Pet;
import com.back.ecomm.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    private PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository){
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> findPetByStatus(List<String> status) {
        return null;
    }

    @Override
    public Pet getPet(Long id) {
        return null;
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> petList = new ArrayList<>();
         petRepository.findAll().forEach(petEntity -> mapTo(petEntity));
        return petList;
    }

    @Override
    public Pet save(Pet pet) {
        PetEntity entity = mapToEntity(pet);
        petRepository.save(entity);
        return mapTo(entity) ;
    }

    private Pet mapTo(PetEntity entity){
        Pet pet = new Pet();
        pet.setName(entity.getName());
        pet.setStatus(Pet.StatusEnum.AVAILABLE);
        pet.setCategory(pet.getCategory());
        return pet;
    }

    private PetEntity mapToEntity(Pet pet){
        Category catEntity = new Category();
        catEntity.setName(pet.getCategory().getName());
        PetEntity petEntity = new PetEntity();
        petEntity.setId(pet.getId());
        petEntity.setCategory(catEntity);
        petEntity.setStatus(pet.getStatus().toString());

        return petEntity;
    }
}
