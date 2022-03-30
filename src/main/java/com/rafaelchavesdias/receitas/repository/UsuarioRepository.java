package com.rafaelchavesdias.receitas.repository;

import com.rafaelchavesdias.receitas.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    Usuario findByUsername(String username);

}
