package com.goinside.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.goinside.jpa2.dao.ClienteDAO;
import com.goinside.jpa2.model.Cliente;
import com.goinside.jpa2.service.NegocioException;
import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;

public class ClienteDAOTest {

	private JIntegrity helper = new JIntegrity();

	private ClienteDAO clienteDAO;

	@Before
	public void init() {
		helper.cleanAndInsert();

		this.clienteDAO = new ClienteDAO();
		this.clienteDAO.setEntityManager(JPAHelper.currentEntityManager());
	}

	@Test
	public void buscarClientePeloIdTest() {
		Cliente cliente = this.clienteDAO.buscarPeloId(4L);
		assertEquals("Duda", cliente.getNome());
	}

	@Test
	public void buscarPessoasTest() {
		List<Cliente> clientes = this.clienteDAO.buscarTodos();
		assertEquals(3, clientes.size());
		
	}

	@Test
	public void edicaoClienteTest() {
		Cliente cliente = this.clienteDAO.buscarPeloId(6L);
		cliente.setNome("Nome Cliente Editado");
		this.clienteDAO.salvar(cliente);
		Cliente clienteModidifado = this.clienteDAO.buscarPeloId(6L);
		assertEquals("Nome Cliente Editado", clienteModidifado.getNome());
		
	}
	
	@Test
	public void exclusaoClienteTest() throws NegocioException {
		Cliente cliente = this.clienteDAO.buscarPeloId(5L);
		this.clienteDAO.excluir(cliente);
		Cliente clienteExcluido = this.clienteDAO.buscarPeloId(5L);
		
		assertEquals(null, clienteExcluido);
	}
}
