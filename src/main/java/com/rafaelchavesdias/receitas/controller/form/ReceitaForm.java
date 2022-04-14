package com.rafaelchavesdias.receitas.controller.form;

import com.rafaelchavesdias.receitas.model.Ingrediente;
import com.rafaelchavesdias.receitas.model.Receita;
import com.rafaelchavesdias.receitas.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
public class ReceitaForm {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String nome;
    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String descricao;
    @NotNull
    @NotEmpty
    private String tipoDeReceita;
    @NotNull
    @NotEmpty
    @Size(min = 1)
    private Ingrediente[] ingrediente;
    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String preparo;

    private Usuario autor;


    public Receita converter() {
        return new Receita(nome, descricao, tipoDeReceita, ingrediente, preparo,autor);
    }
}
