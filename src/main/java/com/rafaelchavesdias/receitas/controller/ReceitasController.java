package com.rafaelchavesdias.receitas.controller;

import com.rafaelchavesdias.receitas.controller.dto.ReceitaDto;
import com.rafaelchavesdias.receitas.controller.form.ReceitaForm;
import com.rafaelchavesdias.receitas.model.Receita;
import com.rafaelchavesdias.receitas.repository.ReceitaRepository;
import com.rafaelchavesdias.receitas.repository.TipoDeRecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/receitas")
public class ReceitasController {

    @Autowired
    private ReceitaRepository receitaRepository;

    private TipoDeRecRepository tipoDeReceitaRepository;


    @GetMapping
    public List<ReceitaDto> lista(String nomeReceita) {
        if (nomeReceita == null) {
            List<Receita> receitas = receitaRepository.findAll();
            return ReceitaDto.converter(receitas);
        } else {
            // List<Receita> receitas = receitaRepository.findByReceitaNome(nomeReceita);
            List<Receita> receitas = receitaRepository.findAll();
            return ReceitaDto.converter(receitas);
        }
    }

    @PostMapping
    @Transactional
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ReceitaDto> cadastrar(@RequestBody @Valid ReceitaForm form, UriComponentsBuilder uriBuilder) {
        Receita receita = form.converter();
        receitaRepository.save(receita);

        URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(receita.getId()).toUri();
        return ResponseEntity.created(uri).body(new ReceitaDto(receita));
    }


}
