package com.rafaelchavesdias.receitas.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.password.PasswordEncoder;


@Getter
@Setter
@EqualsAndHashCode
@Document(collection = "user")
public class Usuario {


    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private String role;

    public Usuario(String username, String email, String password, String role){
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
