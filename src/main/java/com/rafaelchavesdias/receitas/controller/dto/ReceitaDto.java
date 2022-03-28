package com.rafaelchavesdias.receitas.controller.dto;

import com.rafaelchavesdias.receitas.model.Receita;
import com.rafaelchavesdias.receitas.model.TipoDeReceita;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ReceitaDto {

    private String id;
    private String tipoDeReceita;
    private String nome;
    private String descricao;

    public ReceitaDto(Receita receita){
        this.id = receita.getId();
        this.tipoDeReceita = receita.getTipoDeReceita();
        this.nome = receita.getNome();
        this.descricao = receita.getDescricao();
    }


    public static List<ReceitaDto> converter(List<Receita> receitas) {
        return receitas.stream().map(ReceitaDto::new).collect(Collectors.toList());
    }
}
