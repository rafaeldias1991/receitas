package com.rafaelchavesdias.receitas.service;

import com.rafaelchavesdias.receitas.model.Receita;
import com.rafaelchavesdias.receitas.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ReceitaService {

    public Page<Receita> obterReceitas(Pageable paginacao);

    public Receita criar(Receita receita, Usuario usuario);

    public Page<Receita> buscaReceita(String nome,Pageable paginacao);

    public ResponseEntity excluir(String Receitanome);


}
