package com.rafaelchavesdias.receitas.controller;

import com.rafaelchavesdias.receitas.controller.dto.ReceitaDto;
import com.rafaelchavesdias.receitas.controller.form.ReceitaForm;
import com.rafaelchavesdias.receitas.controller.form.UsuarioForm;
import com.rafaelchavesdias.receitas.model.Receita;
import com.rafaelchavesdias.receitas.model.Usuario;
import com.rafaelchavesdias.receitas.service.ReceitaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;



@RestController
@RequestMapping("/receitas")
public class ReceitasController {

    @Autowired
    private ReceitaServiceImp receitaServiceImp;


    @GetMapping
    @Cacheable(value = "listaReceitas")
    public Page<ReceitaDto> lista(
            @RequestParam (required = false)String nomeReceita,
            @PageableDefault(sort = "id",direction = Sort.Direction.ASC) Pageable paginacao) {
        if (nomeReceita == null) {
            Page<Receita> receitas = receitaServiceImp.obterReceitas(paginacao);
            return ReceitaDto.converter(receitas);
        } else {
            Page<Receita> receita = receitaServiceImp.buscaReceita(nomeReceita,paginacao);
            return ReceitaDto.converter(receita);
        }
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaReceitas",allEntries = true)
    public ResponseEntity<ReceitaDto> cadastrar(@RequestParam UsuarioForm forms, @RequestBody @Valid ReceitaForm form, UriComponentsBuilder uriBuilder) {
        Receita receita = form.converter();
        Usuario usuario = forms.converter();
        receitaServiceImp.criar(receita,usuario);
        URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(receita.getId()).toUri();
        return ResponseEntity.created(uri).body(new ReceitaDto(receita));
    }

    @DeleteMapping(path = "/delete/{id}")
    @Transactional
    @CacheEvict(value = "listaReceitas",allEntries = true)
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        ResponseEntity response = receitaServiceImp.excluir(id);
        return response;
    }

}
