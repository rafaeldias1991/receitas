package com.rafaelchavesdias.receitas.repository;

import com.rafaelchavesdias.receitas.model.Receita;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDeRecRepository extends MongoRepository<Receita,Long> {

}
