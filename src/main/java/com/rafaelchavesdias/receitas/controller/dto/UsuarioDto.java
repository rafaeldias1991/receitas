package com.rafaelchavesdias.receitas.controller.dto;

import com.rafaelchavesdias.receitas.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UsuarioDto {

    private String id;
    private String username;
    private String email;


    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.username = usuario.getUsername();
    }

    public static List<UsuarioDto> converter(List<Usuario> usuario) {
        return usuario.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }
}
