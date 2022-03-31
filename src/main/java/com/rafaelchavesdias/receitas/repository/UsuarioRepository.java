package com.rafaelchavesdias.receitas.repository;

import com.rafaelchavesdias.receitas.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    Usuario findByUsername(String username);

}
