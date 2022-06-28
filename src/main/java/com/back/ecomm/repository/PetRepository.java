package com.back.ecomm.repository;

import com.back.ecomm.entity.PetEntity;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<PetEntity, Long> {
}
