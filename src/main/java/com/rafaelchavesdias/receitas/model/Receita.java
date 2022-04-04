package com.rafaelchavesdias.receitas.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document
public class Receita {

    @Id
    private String id;
    private String nome;
    private String descricao;

    private String tipoDeReceita;
    private Ingrediente[] ingrediente;
    private String preparo;

    @DBRef
    private Usuario autor;

    public Receita(String nome, String descricao, String tipoDeReceita, Ingrediente[] ingrediente, String preparo,Usuario autor) {
        this.nome = nome;
        this.descricao = descricao;
        this.tipoDeReceita = tipoDeReceita;
        this.ingrediente = ingrediente;
        this.preparo = preparo;
        this.autor = autor;
    }


}
