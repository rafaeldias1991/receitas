package com.rafaelchavesdias.receitas.controller.form;

import com.rafaelchavesdias.receitas.model.Receita;
import com.rafaelchavesdias.receitas.model.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsuarioForm {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String username;
    @NotNull
    @NotEmpty
    @Email
    private String email;
    @NotNull
    @NotEmpty
    @Length(min = 8, max = 15)
    private String password;
    @NotNull
    @NotEmpty
    private String role;

    private Receita receita;

    public Usuario converter() {
        return new Usuario(username, email, password, role,receita);
    }
}
