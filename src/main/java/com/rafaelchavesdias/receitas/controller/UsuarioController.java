package com.rafaelchavesdias.receitas.controller;

import com.rafaelchavesdias.receitas.config.validacao.ErroEmail;
import com.rafaelchavesdias.receitas.controller.dto.ReceitaDto;
import com.rafaelchavesdias.receitas.model.Receita;
import com.rafaelchavesdias.receitas.model.Usuario;
import com.rafaelchavesdias.receitas.controller.dto.UsuarioDto;
import com.rafaelchavesdias.receitas.controller.form.UsuarioForm;
import com.rafaelchavesdias.receitas.service.receita.ReceitaService;
import com.rafaelchavesdias.receitas.service.usuario.UsuarioService;
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
    private UsuarioService usuarioService;
    @Autowired
    private ReceitaService receitaService;


    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioService.criaUsuario(form);
        if (usuario == null){
            return ResponseEntity.badRequest().body(new ErroEmail(form.getEmail(), "Usuario ja cadastrado no Email"));
        }
        URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }

    @GetMapping("/minhasreceitas")
    public Page<ReceitaDto> listaReceitaUsuario(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable paginacao) {
        String nomeAutor = SecurityContextHolder.getContext().getAuthentication().getName();
        Page<Receita> receitas = receitaService.buscaReceitasPorAutor(nomeAutor, paginacao);
        return ReceitaDto.converter(receitas);

    }


}
