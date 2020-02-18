package com.goinside.jpa2.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.goinside.jpa2.dao.ClienteDAO;
import com.goinside.jpa2.dao.ItemPedidoDAO;
import com.goinside.jpa2.dao.LivroDAO;
import com.goinside.jpa2.dao.VendedorDAO;
import com.goinside.jpa2.model.Cliente;
import com.goinside.jpa2.model.ItemPedido;
import com.goinside.jpa2.model.Livro;
import com.goinside.jpa2.model.Vendedor;
import com.goinside.jpa2.util.jpa.Transactional;

public class CadastroItemPedidoService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ItemPedidoDAO itemPedidoDAO;

	@Inject
	private LivroDAO livroDAO;

	@Inject
	private ClienteDAO clienteDAO;

	@Inject
	private VendedorDAO vendedorDAO;

	@Transactional
	public void salvar(ItemPedido itemPedido) throws NegocioException {
		this.itemPedidoDAO.salvar(itemPedido);
	}

	public List<ItemPedido> buscarItensPorPedido(Long pedidoId){
		return this.itemPedidoDAO.buscarItensPorPedido(pedidoId);
	}
	public List<Cliente> getTodosClientes() {
		return clienteDAO.buscarTodos();
	}

	public List<Vendedor> getTodosVendedores() {
		return vendedorDAO.buscarTodos();
	}

	public List<Livro> getTodosLivros() {
		return livroDAO.buscarTodos();
	}

}