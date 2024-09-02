package com.back.ecomm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name="user_table")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;
    private String username;
    private String password;
    private String mail;
    @Column(name ="enabled")
    private boolean enabled;
    public User(){
        super();
        this.enabled=false;
    }

    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="user_roles",
    joinColumns = @JoinColumn(name="user_id", referencedColumnName = "user_id"),
    inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(() -> "read");
    }


}
