package com.rafaelchavesdias.receitas.repository;

import com.rafaelchavesdias.receitas.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

   Usuario findByUsername(String username);

    Optional<Usuario> findByEmail(String email);

}
