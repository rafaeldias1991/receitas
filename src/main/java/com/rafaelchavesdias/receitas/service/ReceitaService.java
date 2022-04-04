package com.rafaelchavesdias.receitas.service;

import com.rafaelchavesdias.receitas.model.Receita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ReceitaService {

    Page<Receita> obterReceitas(Pageable paginacao);

    Receita criar(Receita receita, String idUsuario);

    Page<Receita> buscaReceita(String nome, Pageable paginacao);

    ResponseEntity excluir(String id, String nomeAutor);


    void addReceitaUsuario(Receita receita,String nomeAutor);
}
