package com.rafaelchavesdias.receitas.controller;

import com.rafaelchavesdias.receitas.controller.dto.ReceitaDto;
import com.rafaelchavesdias.receitas.model.Receita;
import com.rafaelchavesdias.receitas.model.Usuario;
import com.rafaelchavesdias.receitas.repository.UsuarioRepository;
import com.rafaelchavesdias.receitas.controller.dto.UsuarioDto;
import com.rafaelchavesdias.receitas.controller.form.UsuarioForm;
import com.rafaelchavesdias.receitas.service.ReceitaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ReceitaServiceImp receitaServiceImp;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
        form.setPassword(encoder.encode(form.getPassword()));
        Usuario usuario = form.converter();
        usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }

    @GetMapping
    public Page<ReceitaDto> listaReceitaUsuario(
            @PageableDefault(sort = "id",direction = Sort.Direction.ASC) Pageable paginacao) {
        String nomeAutor = SecurityContextHolder.getContext().getAuthentication().getName();

            Page<Receita> receitas = receitaServiceImp.buscaReceitasPorAutor(nomeAutor,paginacao);
            return ReceitaDto.converter(receitas);



    }


}
