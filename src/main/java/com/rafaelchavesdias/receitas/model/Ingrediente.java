package com.rafaelchavesdias.receitas.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Ingrediente {
    @Id
    private String id;
    private String nome;
    private String quantidade;

}
