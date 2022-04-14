package com.rafaelchavesdias.receitas.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@Document(collection = "user")
@AllArgsConstructor
public class Usuario implements UserDetails {

    private static final long serialVersionUID = 1L;


    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    @DBRef
    private Receita receitas = null;
    private String role;

    public Usuario(String username, String email, String password, String role,Receita receitas){
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.receitas = receitas;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
