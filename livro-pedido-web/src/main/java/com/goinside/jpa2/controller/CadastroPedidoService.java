package com.goinside.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.goinside.jpa2.dao.PedidoDAO;
import com.goinside.jpa2.model.Pedido;
import com.goinside.jpa2.service.NegocioException;
import com.goinside.jpa2.util.jpa.Transactional;

public class CadastroPedidoService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoDAO pedidoDAO;
	
	@Transactional
	public void salvar(Pedido pedido) {
		this.pedidoDAO.salvar(pedido);
	}

	public List<Pedido> getTotosPedidos() {
		return pedidoDAO.buscarTodos();
	}
	
	public void excluir(Pedido pedido) throws NegocioException{
		this.pedidoDAO.excluir(pedido);
	}

}
