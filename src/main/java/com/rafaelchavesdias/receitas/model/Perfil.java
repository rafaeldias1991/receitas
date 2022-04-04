package com.rafaelchavesdias.receitas.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Document
public class Perfil implements GrantedAuthority {

    @Id
    private Long id;

    private String nome;

    @Override
    public String getAuthority() {
        return nome;
    }
}
