package com.back.ecomm.repository;

import com.back.ecomm.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "country", path = "countries")
public interface CountryRepository extends JpaRepository<Country, Long> {
}
