package com.rafaelchavesdias.receitas.service.receita;

import com.rafaelchavesdias.receitas.model.Ingrediente;
import com.rafaelchavesdias.receitas.model.Receita;
import com.rafaelchavesdias.receitas.model.Usuario;
import com.rafaelchavesdias.receitas.repository.ReceitaRepository;
import com.rafaelchavesdias.receitas.repository.UsuarioRepository;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


class ReceitaServiceImpTest {
    @Autowired
    private ReceitaServiceImp receitaServiceImp;

    @Mock
    private ReceitaRepository receitaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.receitaServiceImp = new ReceitaServiceImp(receitaRepository, usuarioRepository);


    }

    PageRequest paginacao = PageRequest.of(1, 10);

    @Test
    void obterReceitas() {
        Mockito.when(receitaRepository.findAll(paginacao)).thenReturn(new PageImpl<>(receitas()));
        Assert.assertNotNull(receitas());
        Assert.assertEquals(1,receitas().size());
    }

    @Test
    void deveSalvarUmaReceita() {
        Usuario usuario = new Usuario("rafael", "rafafel@gmail.com", "12345", "ADMIN", null);
        Receita receita = receitas().get(0);
        Mockito.when(usuarioRepository.findByUsername("rafael")).thenReturn(usuario);
        Assert.assertNotNull(usuario);
        Assert.assertEquals("rafael", usuario.getUsername());
        receitaServiceImp.criar(receita, "rafael");
        Mockito.verify(receitaRepository).save(receita);

    }

    @Test
    void buscaReceitasPorAutor() {
        ObjectId id = new ObjectId("5ba7fd625786543ee8260ce2");
        Mockito.when(receitaRepository.findByAutorUsername(id, paginacao)).thenReturn(new PageImpl<>(receitas()));
        Assert.assertNotNull(receitas());
        Assert.assertEquals(1, receitas().size());
        Assert.assertEquals(id.toString(), receitas().get(0).getId());
    }

    @Test
    void excluir() {

        String idReceita = "5ba7fd625786543ee8260ce2";
        Mockito.when(usuarioRepository.findByUsername("rafael")).thenReturn(usuario());
        Mockito.when(receitaRepository.findById(idReceita)).thenReturn(Optional.of(receitas().get(0)));
        Assert.assertNotNull(receitas().get(0));
        Assert.assertNotNull(usuario());
        Assert.assertEquals("rafael", receitas().get(0).getAutor().getUsername());
        receitaRepository.deleteById(idReceita);
        receitaRepository.findById(idReceita);
        usuarioRepository.findByUsername("rafael");
        Mockito.verify(receitaRepository).deleteById(idReceita);
        Mockito.verify(receitaRepository).findById(idReceita);
        Mockito.verify(usuarioRepository).findByUsername("rafael");

    }

    @Test
    void buscaIdUsuario() {
        String nomeUsuario = "rafael";
        Mockito.when(usuarioRepository.findByUsername(nomeUsuario)).thenReturn(usuario());
        usuarioRepository.findByUsername(nomeUsuario);
        Assert.assertNotNull(usuario());
        Assert.assertEquals(nomeUsuario,usuario().getUsername());
        Assert.assertNotNull(usuario().getId());
        Mockito.verify(usuarioRepository).findByUsername(nomeUsuario);
    }

    private List<Receita> receitas() {
        List<Receita> listaReceitas = new ArrayList<>();
        List<Ingrediente> ingredientes = new ArrayList<>();
        ingredientes.add(new Ingrediente("", "ovo", "1"));
        Usuario usuario = new Usuario("rafael", "rafafel@gmail.com", "12345", "ADMIN", null);
        Receita receita = new Receita("5ba7fd625786543ee8260ce2", "bolo", "qualquer coias", "sobremesa", ingredientes.toArray(new Ingrediente[0]), "junta tudo", usuario);
        listaReceitas.add(receita);
        return listaReceitas;
    }

    private Usuario usuario() {
        Usuario usuario = new Usuario("12345", "rafael", "rafafel@gmail.com", "12345", null, "ADMIN");
        return usuario;
    }
}