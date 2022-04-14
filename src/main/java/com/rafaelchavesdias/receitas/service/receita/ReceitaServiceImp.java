package com.rafaelchavesdias.receitas.service.receita;

import com.rafaelchavesdias.receitas.model.Receita;
import com.rafaelchavesdias.receitas.model.Usuario;
import com.rafaelchavesdias.receitas.repository.ReceitaRepository;
import com.rafaelchavesdias.receitas.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReceitaServiceImp implements ReceitaService {


    private  ReceitaRepository receitaRepository;


    private  UsuarioRepository usuarioRepository;

    @Autowired
    public ReceitaServiceImp(ReceitaRepository receitaRepository, UsuarioRepository usuarioRepository) {
        this.receitaRepository = receitaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Page<Receita> obterReceitas(Pageable paginacao) {
        return this.receitaRepository.findAll(paginacao);
    }

    @Override
    public Receita criar(Receita receita, String nomeAutor) {
        Usuario autor = usuarioRepository.findByUsername(nomeAutor);
        receita.setAutor(autor);
        receitaRepository.save(receita);
        return receita;
    }

    @Override
    public Page<Receita> buscaReceita(String nomeReceita, Pageable paginacao) {
        return this.receitaRepository.findByNome(nomeReceita, paginacao);
    }

    public Page<Receita> buscaReceitasPorAutor(String nome, Pageable paginacao) {
        ObjectId id = new ObjectId(buscaIdUsuario(nome));
        return this.receitaRepository.findByAutorUsername(id, paginacao);
    }

    @Override
    public ResponseEntity excluir(String id, String nomeAutor) {
        Usuario autor = usuarioRepository.findByUsername(nomeAutor);
        Optional<Receita> optional = receitaRepository.findById(id);
        // verifica se tem uma receita com esse id no banco
        if (optional.isPresent()) {
            //verifica se o id do autor da receita e o mesmo do usuario que quer deletar
            if (autor.getId().equals(optional.get().getAutor().getId())) {
                receitaRepository.deleteById(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não é o Autor da Receita");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada Receita por esse id");
    }


    public String buscaIdUsuario(String nomeUsuario) {
        String idUsername = usuarioRepository.findByUsername(nomeUsuario).getId();
        return idUsername;
    }


}
