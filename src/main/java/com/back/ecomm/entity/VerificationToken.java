package com.back.ecomm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="verification_token")
public class VerificationToken {

    private static  final int EXPIRATION= 60*24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable=false, name="user_id")
    private User user;

    private Date expireDate;

    private Date calculateExpiryDate(int expiryTimeMinutes){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, expiryTimeMinutes);
        return new Date(calendar.getTime().getTime());
    }

    public VerificationToken(String token, User user){
        this.token = token;
        this.user = user;
    }

}
