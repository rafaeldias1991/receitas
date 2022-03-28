package com.rafaelchavesdias.receitas.controller.dto;

import com.rafaelchavesdias.receitas.model.Usuario;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UsuarioDto {

    private String id;
    private String username;
    private String email;

    public UsuarioDto(Usuario usuario){
        this.id = getId();
        this.email = getEmail();
        this.username = getUsername();
    }

    public static List<UsuarioDto> converter(List<Usuario> usuario) {
        return usuario.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }
}
