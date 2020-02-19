package com.goinside.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.goinside.jpa2.dao.LivroDAO;
import com.goinside.jpa2.model.Livro;
import com.goinside.jpa2.service.NegocioException;
import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;

public class LivroDAOTest {
	private JIntegrity helper = new JIntegrity();

	private LivroDAO livroDAO;

	@Before
	public void init() {
		helper.cleanAndInsert();

		this.livroDAO = new LivroDAO();
		this.livroDAO.setEntityManager(JPAHelper.currentEntityManager());
	}

	@Test
	public void buscarLivroPeloIdTest() {
		Livro livro = this.livroDAO.buscarPeloId(1L);
		assertEquals("Mindset", livro.getTitulo());
	}

	@Test
	public void buscarLivrosTest() {
		List<Livro> livros = this.livroDAO.buscarTodos();
		assertEquals(4, livros.size());
		
	}

	@Test
	public void edicaoLivroTest() {
		Livro livro = this.livroDAO.buscarPeloId(2L);
		livro.setTitulo("Nome Livro Editado");
		this.livroDAO.salvar(livro);
		Livro livroModidifado = this.livroDAO.buscarPeloId(2L);
		assertEquals("Nome Livro Editado", livroModidifado.getTitulo());
		
	}
	
	@Test
	public void exclusaoLivroTest() throws NegocioException {
		Livro livro = this.livroDAO.buscarPeloId(4L);
		this.livroDAO.excluir(livro);
		Livro livroExcluido = this.livroDAO.buscarPeloId(4L);
		
		assertEquals(null, livroExcluido);
	}

}
