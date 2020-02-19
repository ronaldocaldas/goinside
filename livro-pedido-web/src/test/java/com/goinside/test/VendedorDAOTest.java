package com.goinside.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.goinside.jpa2.dao.VendedorDAO;
import com.goinside.jpa2.model.Vendedor;
import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;

public class VendedorDAOTest {
	
	private JIntegrity helper = new JIntegrity();

	private VendedorDAO vendedorDAO;

	@Before
	public void init() {
		helper.cleanAndInsert();

		this.vendedorDAO = new VendedorDAO();
		this.vendedorDAO.setEntityManager(JPAHelper.currentEntityManager());
	}

	@Test
	public void buscarVendedorsPeloIdTest() {
		Vendedor vendedor = this.vendedorDAO.buscarPeloId(1L);
		assertEquals("Pedro", vendedor.getNome());
	}

	@Test
	public void buscarVendedorsTest() {
		List<Vendedor> vendedors = this.vendedorDAO.buscarTodos();
		assertEquals(3, vendedors.size());
	}
	
	@Test
	public void edicaoVendedorTest() {
		Vendedor vendedor = this.vendedorDAO.buscarPeloId(1L);
		vendedor.setNome("Nome Vendedor Editado");
		this.vendedorDAO.salvar(vendedor);
		Vendedor vendedorModidifado = this.vendedorDAO.buscarPeloId(1L);
		assertEquals("Nome Vendedor Editado", vendedorModidifado.getNome());
	}
}
