package com.goinside.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.goinside.jpa2.model.Pedido;
import com.goinside.jpa2.service.NegocioException;
import com.goinside.jpa2.util.jpa.Transactional;

public class PedidoDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1844433409170408406L;
	@Inject
	private EntityManager manager;

	public Pedido buscarPeloId(Long id) {
		return manager.find(Pedido.class, id);
	}

	public void salvar(Pedido pedido) {
		manager.merge(pedido);
	}

	public List<Pedido> buscarTodos() {
		return manager.createQuery("from Pedido", Pedido.class).getResultList();
	}

	@Transactional
	public void excluir(Pedido pedido) throws NegocioException {
		pedido = buscarPeloId(pedido.getId());
		try {
			manager.remove(pedido);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Pedido não pode ser excluído.");
		}
	}

	public Pedido buscarPedidoPelaNome(String nome) {
		return manager.createQuery("select c from Pedido c where c.nome like :nome", Pedido.class)
				.setParameter("nome", "%" + nome + "%").getSingleResult();
	}
}
