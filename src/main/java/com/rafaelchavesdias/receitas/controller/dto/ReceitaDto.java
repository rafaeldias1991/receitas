package com.rafaelchavesdias.receitas.controller.dto;

import com.rafaelchavesdias.receitas.model.Receita;
import com.rafaelchavesdias.receitas.model.TipoDeReceita;
import com.rafaelchavesdias.receitas.model.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ReceitaDto {

    private String id;
    private String tipoDeReceita;
    private String nome;
    private String descricao;
    private UsuarioDto autor;


    public ReceitaDto(Receita receita) {
        this.id = receita.getId();
        this.tipoDeReceita = receita.getTipoDeReceita();
        this.nome = receita.getNome();
        this.descricao = receita.getDescricao();
        this.autor = new UsuarioDto(receita.getAutor());
    }


    public static Page<ReceitaDto> converter(Page<Receita> receitas) {
        return receitas.map(ReceitaDto::new);
    }
}
