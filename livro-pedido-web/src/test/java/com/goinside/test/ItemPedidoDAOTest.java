package com.goinside.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.goinside.jpa2.dao.ItemPedidoDAO;
import com.goinside.jpa2.model.ItemPedido;
import com.goinside.jpa2.service.NegocioException;
import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;

public class ItemPedidoDAOTest {

	private JIntegrity helper = new JIntegrity();

	private ItemPedidoDAO itemPedidoDAO;

	@Before
	public void init() {
		helper.cleanAndInsert();

		this.itemPedidoDAO = new ItemPedidoDAO();
		this.itemPedidoDAO.setEntityManager(JPAHelper.currentEntityManager());
	}

	@Test
	public void buscarClientePeloIdTest() {
		ItemPedido itemPedido = this.itemPedidoDAO.buscarPeloId(1L);
		assertEquals("Duda", itemPedido.getCliente().getNome());
		assertEquals("45582915583", itemPedido.getLivro().getIsbn());
		assertEquals("Pedro", itemPedido.getVendedor().getNome());
	}

	@Test
	public void buscarItensPedidoTest() {
		List<ItemPedido> itens = this.itemPedidoDAO.buscarTodos();
		assertEquals(4, itens.size());
	}
	
	@Test
	public void exclusaoItemPedidoTest() throws NegocioException {
		ItemPedido item = this.itemPedidoDAO.buscarPeloId(4L);
		this.itemPedidoDAO.excluir(item);
		ItemPedido itemExcluido = this.itemPedidoDAO.buscarPeloId(4L);
		
		assertEquals(null, itemExcluido);
	}

}
