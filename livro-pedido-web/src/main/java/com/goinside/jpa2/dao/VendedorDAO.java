package com.goinside.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.goinside.jpa2.model.Vendedor;
import com.goinside.jpa2.service.NegocioException;
import com.goinside.jpa2.util.jpa.Transactional;

public class VendedorDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1286178768125800218L;
	@Inject
	private EntityManager manager;

	public Vendedor buscarPeloId(Long id) {
		return manager.find(Vendedor.class, id);
	}

	public void salvar(Vendedor vendedor) {
		manager.merge(vendedor);
	}

	public List<Vendedor> buscarTodos() {
		return manager.createQuery("from Vendedor", Vendedor.class).getResultList();
	}

	@Transactional
	public void excluir(Vendedor vendedor) throws NegocioException {
		vendedor = buscarPeloId(vendedor.getId());
		try {
			manager.remove(vendedor);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Vendedor não pode ser excluído.");
		}
	}

	public Vendedor buscarVendedorPelaNome(String nome) {
		return manager.createQuery("select c from Vendedor c where c.nome like :nome", Vendedor.class)
				.setParameter("nome", "%" + nome + "%").getSingleResult();
	}

	public void setEntityManager(EntityManager manager) {
		this.manager = manager;
	}
}
