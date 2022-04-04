package com.rafaelchavesdias.receitas.service;

import com.rafaelchavesdias.receitas.model.Receita;
import com.rafaelchavesdias.receitas.model.Usuario;
import com.rafaelchavesdias.receitas.repository.ReceitaRepository;
import com.rafaelchavesdias.receitas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReceitaServiceImp implements ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Page<Receita> obterReceitas(Pageable paginacao) {
        return this.receitaRepository.findAll(paginacao);
    }

    @Override
    public Receita criar(Receita receita, String nomeAutor) {
        Usuario autor = usuarioRepository.findByUsername(nomeAutor);
        autor.setReceitas(receita);
       // usuarioRepository.save(autor);
        receita.setAutor(autor);
        return this.receitaRepository.save(receita);
    }

    @Override
    public Page<Receita> buscaReceita(String nomeReceita, Pageable paginacao) {
        return this.receitaRepository.findByNome(nomeReceita, paginacao);
    }

    public Page<Receita> buscaReceitasPorAutor(String nome,Pageable paginacao){
        System.out.println(receitaRepository.findByAutor(nome,paginacao));
        return this.receitaRepository.findByAutor(nome,paginacao);
    }

    @Override
    public ResponseEntity excluir(String id, String nomeAutor) {
        Usuario autor = usuarioRepository.findByUsername(nomeAutor);
        Optional<Receita> optional = receitaRepository.findById(id);
        // verifica se tem uma receita com esse id no banco
        if (optional.isPresent()) {
            //verifica se o id do autor da receita e o mesmo do usuario que quer deletar
        if (autor.getId().equals(optional.get().getAutor().getId())){
            receitaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();


    }

    public void addReceitaUsuario(Receita receita, String nomeAutor) {
        Usuario autor = usuarioRepository.findByUsername(nomeAutor);
        Optional<Receita> optional = receitaRepository.findById(receita.getId());



    }


}
