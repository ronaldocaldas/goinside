package com.goinside.test;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.goinside.jpa2.dao.PedidoDAO;
import com.goinside.jpa2.model.Pedido;
import com.goinside.jpa2.service.NegocioException;
import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;

public class PedidoDAOTest {

	private JIntegrity helper = new JIntegrity();

	private PedidoDAO pedidoDAO;

	@Before
	public void init() {
		helper.cleanAndInsert();

		this.pedidoDAO = new PedidoDAO();
		this.pedidoDAO.setEntityManager(JPAHelper.currentEntityManager());
	}

	@Test
	public void buscarPedidoPeloIdTest() {
		Pedido itemPedido = this.pedidoDAO.buscarPeloId(2L);
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		assertEquals("2018-11-12 11:00:00", simpleDateFormat.format(itemPedido.getDataPedido()));
	
	}

	@Test
	public void buscarPedidosTest() {
		List<Pedido> itens = this.pedidoDAO.buscarTodos();
		assertEquals(4, itens.size());
	}
	
	@Test
	public void exclusaoPedidoTest() throws NegocioException {
		Pedido item = this.pedidoDAO.buscarPeloId(4L);
		this.pedidoDAO.excluir(item);
		Pedido itemExcluido = this.pedidoDAO.buscarPeloId(4L);
		
		assertEquals(null, itemExcluido);
	}

}
