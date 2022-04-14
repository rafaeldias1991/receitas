package com.rafaelchavesdias.receitas.controller;

import com.rafaelchavesdias.receitas.controller.dto.ReceitaDto;
import com.rafaelchavesdias.receitas.controller.form.ReceitaForm;
import com.rafaelchavesdias.receitas.model.Receita;
import com.rafaelchavesdias.receitas.service.receita.ReceitaServiceImp;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;



@RestController
@RequestMapping("/receitas")

public class ReceitasController {


    private final ReceitaServiceImp receitaServiceImp;

    @Autowired
    public ReceitasController(ReceitaServiceImp receitaServiceImp) {
        this.receitaServiceImp = receitaServiceImp;
    }

    @GetMapping
    @Cacheable(value = "listaReceitas")
    public Page<ReceitaDto> lista(
            @RequestParam (required = false)String nomeReceita,
            @PageableDefault(sort = "id",direction = Sort.Direction.ASC) Pageable paginacao) {
        if (nomeReceita == null || nomeReceita.isEmpty()) {
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
    public ResponseEntity<ReceitaDto> cadastrar( @RequestBody @Valid ReceitaForm form, UriComponentsBuilder uriBuilder){
        String nomeAutor = SecurityContextHolder.getContext().getAuthentication().getName();
        Receita receita = form.converter();
        receitaServiceImp.criar(receita,/*nomeAutor*/"rafael");
        URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(receita.getId()).toUri();
        return ResponseEntity.ok(new ReceitaDto(receita));
    }

    @DeleteMapping(path = "/delete/{id}")
    @Transactional
    @CacheEvict(value = "listaReceitas",allEntries = true)
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        String nomeAutor = SecurityContextHolder.getContext().getAuthentication().getName();
        ResponseEntity response = receitaServiceImp.excluir(id,nomeAutor);
        return response;
    }

}
