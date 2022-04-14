package com.rafaelchavesdias.receitas;

import com.rafaelchavesdias.receitas.controller.ReceitasController;
import com.rafaelchavesdias.receitas.model.Ingrediente;
import com.rafaelchavesdias.receitas.model.Receita;
import com.rafaelchavesdias.receitas.model.Usuario;
import com.rafaelchavesdias.receitas.repository.ReceitaRepository;
import com.rafaelchavesdias.receitas.service.receita.ReceitaServiceImp;
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



class ReceitasApplicationTests {


	@Autowired
	private ReceitasController receitasController;

	@Mock
	private ReceitaServiceImp receitaServiceImp;
	@Mock
	private ReceitaRepository receitaRepository;

	@BeforeEach
	public void beforeEach(){
		MockitoAnnotations.openMocks(this);
		this.receitasController = new ReceitasController(receitaServiceImp);
	}

	PageRequest paginacao = PageRequest.of(1, 10);

	@Test
	void deveRetornarUmaListaDeReceitas() {
		List<Receita> listaReceita = receitas();
		Mockito.when(receitaServiceImp.obterReceitas(paginacao)).thenReturn(new PageImpl<>(listaReceita));
		Assert.assertEquals(1,listaReceita.size());
	}

	@Test
	void deveRetornarUmaReceita(){
		List<Receita> listaReceita = receitas();
		Receita receita = listaReceita.get(0);
		Mockito.when(receitaServiceImp.buscaReceita("bolo",paginacao)).thenReturn(new PageImpl<>(listaReceita));
		Assert.assertEquals("bolo",receita.getNome());
		Assert.assertNotNull(receita);
	}



	private List<Receita> receitas(){
		List<Receita> listaReceitas = new ArrayList<>();
		List<Ingrediente> ingredientes = new ArrayList<>();
		ingredientes.add(new Ingrediente("","ovo","1"));
		Usuario usuario = new Usuario("rafael","rafafel@gmail.com","12345","ADMIN",null);
		Receita receita = new Receita("bolo","qualquer coias","sobremesa",ingredientes.toArray(new Ingrediente[0]),"junta tudo",usuario);
		listaReceitas.add(receita);
		return listaReceitas;
	}

}
