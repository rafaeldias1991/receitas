package com.rafaelchavesdias.receitas.service;

import com.rafaelchavesdias.receitas.model.Receita;
import com.rafaelchavesdias.receitas.model.Usuario;
import com.rafaelchavesdias.receitas.repository.ReceitaRepository;
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
    @Override
    public Page<Receita> obterReceitas(Pageable paginacao) {
        return this.receitaRepository.findAll(paginacao);
    }

    @Override
    public Receita criar(Receita receita, Usuario usuario) {

        receita.setAutor(usuario);
        return this.receitaRepository.save(receita);
    }

    @Override
    public Page<Receita> buscaReceita(String nomeReceita,Pageable paginacao) {
        return this.receitaRepository.findByNome(nomeReceita,paginacao);
    }

    public ResponseEntity excluir(String id) {
        Optional<Receita> optional = receitaRepository.findById(id);
        if (optional.isPresent()){
            receitaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();


    }


}
