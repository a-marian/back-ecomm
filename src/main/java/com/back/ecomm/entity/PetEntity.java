package com.back.ecomm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message= "Name pet required")
    private String name;

    private String pedigree;

    private Integer age;

    private String status;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private Category category;


}
