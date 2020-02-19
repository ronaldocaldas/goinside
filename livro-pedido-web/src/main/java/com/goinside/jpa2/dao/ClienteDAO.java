package com.goinside.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.goinside.jpa2.model.Cliente;
import com.goinside.jpa2.service.NegocioException;
import com.goinside.jpa2.util.jpa.Transactional;

public class ClienteDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1286178768125800218L;
	@Inject
	private EntityManager manager;

	public Cliente buscarPeloId(Long id) {
		return manager.find(Cliente.class, id);
	}

	public void salvar(Cliente cliente) {
		manager.merge(cliente);
	}

	public List<Cliente> buscarTodos() {
		return manager.createQuery("from Cliente", Cliente.class).getResultList();
	}

	@Transactional
	public void excluir(Cliente cliente) throws NegocioException {
		cliente = buscarPeloId(cliente.getId());
		try {
			manager.remove(cliente);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Cliente não pode ser excluído.");
		}
	}

	public Cliente buscarClientePelaNome(String nome) {
		return manager.createQuery("select c from Cliente c where c.nome like :nome", Cliente.class)
				.setParameter("nome", "%" + nome + "%").getSingleResult();
	}
	

	public void setEntityManager(EntityManager manager) {
		this.manager = manager;
	}

}
