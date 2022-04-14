package com.rafaelchavesdias.receitas.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@AllArgsConstructor
public class Ingrediente {
    @Id
    private String id;
    private String nome;
    private String quantidade;



}
