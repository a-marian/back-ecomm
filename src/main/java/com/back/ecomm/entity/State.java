package com.back.ecomm.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="state")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
