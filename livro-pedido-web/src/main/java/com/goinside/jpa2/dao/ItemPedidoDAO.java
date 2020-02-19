package com.goinside.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.goinside.jpa2.model.ItemPedido;
import com.goinside.jpa2.service.NegocioException;
import com.goinside.jpa2.util.jpa.Transactional;

public class ItemPedidoDAO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7498888190265765831L;
	@Inject
	private EntityManager manager;

	public ItemPedido buscarPeloId(Long id) {
		return manager.find(ItemPedido.class, id);
	}

	public void salvar(ItemPedido itemPedido) {
		manager.merge(itemPedido);
	}

	public List<ItemPedido> buscarTodos() {
		return manager.createQuery("from ItemPedido", ItemPedido.class).getResultList();
	}

	public List<ItemPedido> buscarItensPorPedido(Long pedidoId) {
		return manager.createQuery("from ItemPedido ip where ip.pedido.id = :pedidoId ", ItemPedido.class)
				.setParameter("pedidoId", pedidoId).getResultList();
	}

	@Transactional
	public void excluir(ItemPedido itemPedido) throws NegocioException {
		itemPedido = buscarPeloId(itemPedido.getId());
		try {
			manager.remove(itemPedido);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Item não pode ser excluído.");
		}
	}

	public void setEntityManager(EntityManager manager) {
		this.manager = manager;
	}
}
