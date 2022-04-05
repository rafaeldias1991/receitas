package com.rafaelchavesdias.receitas.service.usuario;

import com.rafaelchavesdias.receitas.controller.form.UsuarioForm;
import com.rafaelchavesdias.receitas.model.Usuario;
import com.rafaelchavesdias.receitas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Usuario criaUsuario(UsuarioForm form) {
        form.setPassword(encoder.encode(form.getPassword()));
        Usuario usuario = form.converter();
        Optional<Usuario> usuarioEmail = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioEmail.isPresent()){
            return null;
        }
        usuarioRepository.save(usuario);
        return usuario;
    }
}
