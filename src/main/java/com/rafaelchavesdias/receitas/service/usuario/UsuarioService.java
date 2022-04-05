package com.rafaelchavesdias.receitas.service.usuario;

import com.rafaelchavesdias.receitas.controller.form.UsuarioForm;
import com.rafaelchavesdias.receitas.model.Usuario;

public interface UsuarioService {

    Usuario criaUsuario(UsuarioForm form);

}
