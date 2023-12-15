package com.back.ecomm.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="STATE")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="state_id")
    private Long stateId;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public State(String code, String name){
        this.code = code;
        this.name = name;
    }
}
