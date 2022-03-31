package com.rafaelchavesdias.receitas.repository;


import com.rafaelchavesdias.receitas.model.Receita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ReceitaRepository extends MongoRepository<Receita, String> {
    Page<Receita> findByNome(String nome, Pageable paginacao);
}
