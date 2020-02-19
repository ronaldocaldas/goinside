package com.goinside.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.goinside.jpa2.dao.FornecedorDAO;
import com.goinside.jpa2.model.Fornecedor;
import com.goinside.jpa2.service.NegocioException;
import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;

public class FornecedorDAOTest {

	private JIntegrity helper = new JIntegrity();

	private FornecedorDAO fornecedorDAO;

	@Before
	public void init() {
		helper.cleanAndInsert();

		this.fornecedorDAO = new FornecedorDAO();
		this.fornecedorDAO.setEntityManager(JPAHelper.currentEntityManager());
	}

	@Test
	public void buscarFornecedorPeloIdTest() {
		Fornecedor fornecedor = this.fornecedorDAO.buscarPeloId(1L);

		assertEquals(1L, fornecedor.getId().longValue());
		assertEquals("Amazon", fornecedor.getNomeFantasia());
	}

	@Test
	public void buscarFornecedorsTest() {
		List<Fornecedor> fornecedors = this.fornecedorDAO.buscarTodos();
		assertEquals(4, fornecedors.size());
	}

	@Test
	public void edicaoFornecedorTest() {
		Fornecedor fornecedor = this.fornecedorDAO.buscarPeloId(1L);
		fornecedor.setNomeFantasia("Livraria Catarinense");
		this.fornecedorDAO.salvar(fornecedor);
		Fornecedor fornecedorModidifado = this.fornecedorDAO.buscarPeloId(1L);

		assertEquals(1L, fornecedorModidifado.getId().longValue());
		assertEquals("Livraria Catarinense", fornecedorModidifado.getNomeFantasia());
	}

	@Test
	public void exclusaoFornecedorTest() throws NegocioException {
		Fornecedor fornecedor = this.fornecedorDAO.buscarPeloId(3L);
		this.fornecedorDAO.excluir(fornecedor);
		
		Fornecedor fornecedorExcluido = this.fornecedorDAO.buscarPeloId(3L);
		assertEquals(null, fornecedorExcluido);
	}
	
}
