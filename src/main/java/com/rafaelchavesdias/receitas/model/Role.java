package com.rafaelchavesdias.receitas.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Document(collection = "Role")
public class Role implements GrantedAuthority {

    @Id
    private String id;

    private String nome;

    @Override
    public String getAuthority() {
        return nome;
    }
}
