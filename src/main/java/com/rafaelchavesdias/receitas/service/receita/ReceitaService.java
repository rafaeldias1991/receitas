package com.rafaelchavesdias.receitas.service.receita;

import com.rafaelchavesdias.receitas.model.Receita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface ReceitaService {

    Page<Receita> obterReceitas(Pageable paginacao);

    Receita criar(Receita receita, String idUsuario);

    Page<Receita> buscaReceita(String nome, Pageable paginacao);

    ResponseEntity excluir(String id, String nomeAutor);

    String buscaIdUsuario(String nomeUsuario);

    Page<Receita> buscaReceitasPorAutor(String nome,Pageable paginacao);

}
